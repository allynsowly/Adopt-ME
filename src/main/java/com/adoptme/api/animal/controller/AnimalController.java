package com.adoptme.api.animal.controller;

import com.adoptme.api.animal.dto.AnimalRequestDTO;
import com.adoptme.api.animal.dto.AnimalResponseDTO;
import com.adoptme.api.animal.enums.TagSaude;
import com.adoptme.api.animal.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animais")
@Tag(name = "Animais", description = "Gerenciamento de animais disponíveis para adoção")
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    @Operation(summary = "Cadastrar animal", description = "Registra um novo animal no sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Animal cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<AnimalResponseDTO> cadastrar(@RequestBody AnimalRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.cadastrar(dto));
    }

    @Operation(summary = "Listar todos os animais", description = "Retorna todos os animais cadastrados, adotados ou não")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> listarTodos() {
        return ResponseEntity.ok(animalService.listarTodos());
    }

    @Operation(summary = "Buscar animal por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Animal encontrado"),
        @ApiResponse(responseCode = "404", description = "Animal não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<AnimalResponseDTO> animal = animalService.buscarPorId(id);
        return animal.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar animal", description = "Atualiza os dados de um animal existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Animal atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Animal não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody AnimalRequestDTO dto) {
        try {
            return ResponseEntity.ok(animalService.atualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remover animal")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Animal removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Animal não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            animalService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

  
    @Operation(
        summary = "Listar por tempo de espera",
        description = "Retorna animais disponíveis ordenados do que aguarda adoção há mais tempo"
    )
    @GetMapping("/espera")
    public ResponseEntity<List<AnimalResponseDTO>> listarPorTempoDeEspera() {
        return ResponseEntity.ok(animalService.listarPorTempoDeEspera());
    }

    @Operation(
        summary = "Animal com maior tempo de espera",
        description = "Retorna o único animal disponível que está há mais tempo aguardando adoção"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Animal encontrado"),
        @ApiResponse(responseCode = "204", description = "Nenhum animal disponível")
    })
    @GetMapping("/espera/maior")
    public ResponseEntity<AnimalResponseDTO> animalComMaiorTempoDeEspera() {
        Optional<AnimalResponseDTO> animal = animalService.animalComMaiorTempoDeEspera();
        return animal.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.noContent().build());
    }

    @Operation(
        summary = "Filtrar animais da rua",
        description = "Retorna somente animais disponíveis cuja origem é RUA"
    )
    @GetMapping("/filtro/rua")
    public ResponseEntity<List<AnimalResponseDTO>> listarDaRua() {
        return ResponseEntity.ok(animalService.listarAnimaisDaRua());
    }

    @Operation(
        summary = "Filtrar por tag de saúde",
        description = "Retorna animais disponíveis que possuem a tag informada. Exemplo: VACINADO, CASTRADO"
    )
    @ApiResponse(responseCode = "200", description = "Lista filtrada retornada com sucesso")
    @GetMapping("/filtro/saude")
    public ResponseEntity<List<AnimalResponseDTO>> listarPorTagSaude(@RequestParam TagSaude tag) {
        return ResponseEntity.ok(animalService.listarPorTagSaude(tag));
    }
}
