package com.adoptme.api.formulario_adocao.service;

import com.adoptme.api.animal.domain.Animal;
import com.adoptme.api.animal.repository.AnimalRepository;
import com.adoptme.api.formulario_adocao.dto.FormularioAdocaoRequestDto;
import com.adoptme.api.formulario_adocao.dto.FormularioAdocaoResponseDto;
import com.adoptme.api.formulario_adocao.enums.StatusSolicitacao;
import com.adoptme.api.formulario_adocao.exececao.AnimalJaAdotadoException;
import com.adoptme.api.formulario_adocao.mapper.FormularioAdocaoMapper;
import com.adoptme.api.formulario_adocao.model.FormularioAdocao;
import com.adoptme.api.formulario_adocao.repository.FormularioAdocaoRepository;
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
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado com o ID: " + dto.animalId()));

        if (animal.getAdotado()) {
            throw new AnimalJaAdotadoException("Não é possível enviar a solicitação. Este animal já foi adotado!");
        }

        FormularioAdocao formulario = mapper.toEntity(dto);

        formulario.setAnimal(animal);

        FormularioAdocao formularioSalvo = repository.save(formulario);

        return mapper.toDto(formularioSalvo);
    }


    @Transactional
    public void aprovarSolicitacao(Long id) {
        FormularioAdocao formulario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formulário de adoção não encontrado com o ID: " + id));

        if (formulario.getAnimal().getAdotado()) {
            throw new AnimalJaAdotadoException("Este animal já foi adotado através de outra solicitação.");
        }

        formulario.setStatus(StatusSolicitacao.APROVADO);

        formulario.getAnimal().setAdotado(true);

    }


    @Transactional
    public void rejeitarSolicitacao(Long id, String justificativa) {
        FormularioAdocao formulario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formulário de adoção não encontrado com o ID: " + id));

        formulario.setStatus(StatusSolicitacao.REJEITADO);
        formulario.setJustificativaStatus(justificativa);
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
        FormularioAdocao formulario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formulário não encontrado."));
        return mapper.toDto(formulario);
    }
}
