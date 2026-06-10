package com.adoptme.api.animal.service;

import com.adoptme.api.animal.domain.Animal;
import com.adoptme.api.animal.dto.AnimalRequestDTO;
import com.adoptme.api.animal.dto.AnimalResponseDTO;
import com.adoptme.api.animal.enums.OrigemAnimal;
import com.adoptme.api.animal.enums.TagSaude;
import com.adoptme.api.animal.mapper.AnimalMapper;
import com.adoptme.api.animal.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalMapper animalMapper;


    public AnimalResponseDTO cadastrar(AnimalRequestDTO dto) {
        Animal animal = animalMapper.toEntity(dto);
        return animalMapper.toDto(animalRepository.save(animal));
    }

    public List<AnimalResponseDTO> listarTodos() {
        return animalMapper.toDtoList(animalRepository.findAll());
    }

    public Optional<AnimalResponseDTO> buscarPorId(Long id) {
        return animalRepository.findById(id)
                .map(animalMapper::toDto);
    }

    public AnimalResponseDTO atualizar(Long id, AnimalRequestDTO dto) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com id: " + id));

        animalMapper.updateEntityFromDto(dto, animal);

        return animalMapper.toDto(animalRepository.save(animal));
    }

    public void deletar(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Animal não encontrado com id: " + id);
        }
        animalRepository.deleteById(id);
    }


    public List<AnimalResponseDTO> listarPorTempoDeEspera() {
        return animalMapper.toDtoList(
                animalRepository.findDisponiveisOrdenadosPorTempoEspera()
        );
    }

    public Optional<AnimalResponseDTO> animalComMaiorTempoDeEspera() {
        return animalRepository.findDisponiveisOrdenadosPorTempoEspera()
                .stream()
                .findFirst()
                .map(animalMapper::toDto);
    }


    public List<AnimalResponseDTO> listarAnimaisDaRua() {
        return animalMapper.toDtoList(
                animalRepository.findByOrigemAndAdotadoFalse(OrigemAnimal.RUA)
        );
    }

    public List<AnimalResponseDTO> listarPorTagSaude(TagSaude tag) {
        return animalMapper.toDtoList(
                animalRepository.findByTagSaudeAndDisponiveis(tag)
        );
    }
}
