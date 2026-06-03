package com.adoptme.api.animal.service;

import com.adoptme.api.animal.domain.Animal;
import com.adoptme.api.animal.dto.AnimalResponseDTO;
import com.adoptme.api.animal.enums.OrigemAnimal;
import com.adoptme.api.animal.enums.TagSaude;
import com.adoptme.api.animal.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

  
    public AnimalResponseDTO cadastrar(Animal animal) {
        Animal salvo = animalRepository.save(animal);
        return new AnimalResponseDTO(salvo);
    }

    public List<AnimalResponseDTO> listarTodos() {
        return animalRepository.findAll()
                .stream()
                .map(AnimalResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<AnimalResponseDTO> buscarPorId(Long id) {
        return animalRepository.findById(id)
                .map(AnimalResponseDTO::new);
    }

    public AnimalResponseDTO atualizar(Long id, Animal dadosNovos) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com id: " + id));

        animal.setNome(dadosNovos.getNome());
        animal.setRaca(dadosNovos.getRaca());
        animal.setIdadeAnos(dadosNovos.getIdadeAnos());
        animal.setEspecie(dadosNovos.getEspecie());
        animal.setDataEntradaAcolhimento(dadosNovos.getDataEntradaAcolhimento());
        animal.setOrigem(dadosNovos.getOrigem());
        animal.setTagsSaude(dadosNovos.getTagsSaude());
        animal.setAdotado(dadosNovos.getAdotado());

        return new AnimalResponseDTO(animalRepository.save(animal));
    }

    public void deletar(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Animal não encontrado com id: " + id);
        }
        animalRepository.deleteById(id);
    }

   
    public List<AnimalResponseDTO> listarPorTempoDeEspera() {
        return animalRepository.findDisponiveisOrdenadosPorTempoEspera()
                .stream()
                .map(AnimalResponseDTO::new)
                .collect(Collectors.toList());
    }

    
    public Optional<AnimalResponseDTO> animalComMaiorTempoDeEspera() {
        return animalRepository.findDisponiveisOrdenadosPorTempoEspera()
                .stream()
                .findFirst()
                .map(AnimalResponseDTO::new);
    }

   
    public List<AnimalResponseDTO> listarAnimaisDaRua() {
        return animalRepository.findByOrigemAndAdotadoFalse(OrigemAnimal.RUA)
                .stream()
                .map(AnimalResponseDTO::new)
                .collect(Collectors.toList());
    }

   
    public List<AnimalResponseDTO> listarPorTagSaude(TagSaude tag) {
        return animalRepository.findByTagSaudeAndDisponiveis(tag)
                .stream()
                .map(AnimalResponseDTO::new)
                .collect(Collectors.toList());
    }
}
