package com.adoptme.api.formularioadocao.service;

import com.adoptme.api.animal.domain.Animal;
import com.adoptme.api.animal.repository.AnimalRepository;
import com.adoptme.api.formularioadocao.dto.FormularioAdocaoRequestDto;
import com.adoptme.api.formularioadocao.dto.FormularioAdocaoResponseDto;
import com.adoptme.api.formularioadocao.enums.StatusSolicitacao;
import com.adoptme.api.formularioadocao.exception.AnimalJaAdotadoException;
import com.adoptme.api.formularioadocao.mapper.FormularioAdocaoMapper;
import com.adoptme.api.formularioadocao.model.FormularioAdocao;
import com.adoptme.api.formularioadocao.repository.FormularioAdocaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class FormularioAdocaoService {

    private final FormularioAdocaoRepository repository;
    private final FormularioAdocaoMapper mapper;
    private final AnimalRepository animalRepository;

    @Transactional
    public FormularioAdocaoResponseDto criarSolicitacao(FormularioAdocaoRequestDto dto) {

        Animal animal = animalRepository.findById(dto.animalId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Animal não encontrado com o ID: " + dto.animalId()));

        if (Boolean.TRUE.equals(animal.getAdotado())) {
            throw new AnimalJaAdotadoException(
                    "Não é possível enviar a solicitação. Este animal já foi adotado.");
        }

        FormularioAdocao formulario = mapper.toEntity(dto);
        formulario.setAnimal(animal);

        FormularioAdocao salvo = repository.save(formulario);

        log.info("Solicitação de adoção criada. ID: {}", salvo.getId());

        return mapper.toDto(salvo);
    }

    @Transactional
    public void aprovarSolicitacao(Long id) {

        FormularioAdocao formulario = buscarFormulario(id);

        if (formulario.getStatus() == StatusSolicitacao.REJEITADO) {
            throw new IllegalStateException(
                    "Não é possível aprovar uma solicitação rejeitada.");
        }

        if (Boolean.TRUE.equals(formulario.getAnimal().getAdotado())) {
            throw new AnimalJaAdotadoException(
                    "Este animal já foi adotado através de outra solicitação.");
        }

        formulario.setStatus(StatusSolicitacao.APROVADO);
        formulario.getAnimal().setAdotado(true);

        List<FormularioAdocao> solicitacoes =
                repository.findByAnimalId(formulario.getAnimal().getId());

        for (FormularioAdocao solicitacao : solicitacoes) {

            if (!solicitacao.getId().equals(formulario.getId())
                    && solicitacao.getStatus() == StatusSolicitacao.PENDENTE) {

                solicitacao.setStatus(StatusSolicitacao.REJEITADO);

                solicitacao.setJustificativaStatus(
                        "Animal adotado por outro solicitante.");
            }
        }

        log.info("Solicitação aprovada. ID: {}", id);
    }

    @Transactional
    public void rejeitarSolicitacao(Long id, String justificativa) {

        FormularioAdocao formulario = buscarFormulario(id);

        if (formulario.getStatus() == StatusSolicitacao.APROVADO) {
            throw new IllegalStateException(
                    "Não é possível rejeitar uma solicitação aprovada.");
        }

        formulario.setStatus(StatusSolicitacao.REJEITADO);
        formulario.setJustificativaStatus(justificativa);

        log.info("Solicitação rejeitada. ID: {}", id);
    }

    @Transactional(readOnly = true)
    public List<FormularioAdocaoResponseDto> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public FormularioAdocaoResponseDto buscarPorId(Long id) {
        return mapper.toDto(buscarFormulario(id));
    }

    @Transactional
    public void deletarSolicitacao(Long id) {

        FormularioAdocao formulario = buscarFormulario(id);

        repository.delete(formulario);

        log.info("Formulário de adoção deletado com sucesso. ID: {}", id);
    }

    private FormularioAdocao buscarFormulario(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Formulário de adoção não encontrado com o ID: " + id));
    }
}