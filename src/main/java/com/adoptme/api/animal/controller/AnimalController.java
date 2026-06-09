package com.adoptme.api.animal.controller;

import com.adoptme.api.animal.domain.Animal;
import com.adoptme.api.animal.dto.AnimalResponseDTO;
import com.adoptme.api.animal.enums.TagSaude;
import com.adoptme.api.animal.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalResponseDTO> cadastrar(@RequestBody Animal animal) {
        AnimalResponseDTO novoAnimal = animalService.cadastrar(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAnimal);
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> listarTodos() {
        return ResponseEntity.ok(animalService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<AnimalResponseDTO> animal = animalService.buscarPorId(id);
        return animal.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody Animal dadosNovos) {
        try {
            AnimalResponseDTO atualizado = animalService.atualizar(id, dadosNovos);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            animalService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

 
    @GetMapping("/espera")
    public ResponseEntity<List<AnimalResponseDTO>> listarPorTempoDeEspera() {
        return ResponseEntity.ok(animalService.listarPorTempoDeEspera());
    }

    @GetMapping("/espera/maior")
    public ResponseEntity<AnimalResponseDTO> animalComMaiorTempoDeEspera() {
        Optional<AnimalResponseDTO> animal = animalService.animalComMaiorTempoDeEspera();
        return animal.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/filtro/rua")
    public ResponseEntity<List<AnimalResponseDTO>> listarDaRua() {
        return ResponseEntity.ok(animalService.listarAnimaisDaRua());
    }

    @GetMapping("/filtro/saude")
    public ResponseEntity<List<AnimalResponseDTO>> listarPorTagSaude(
            @RequestParam TagSaude tag) {
        return ResponseEntity.ok(animalService.listarPorTagSaude(tag));
    }
}
