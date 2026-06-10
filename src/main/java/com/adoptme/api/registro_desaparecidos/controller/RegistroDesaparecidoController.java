package com.adoptme.api.registro_desaparecidos.controller;

import com.adoptme.api.registro_desaparecidos.domain.RegistroDesaparecido;
import com.adoptme.api.registro_desaparecidos.dto.AtualizarCoordenadasDTO;
import com.adoptme.api.registro_desaparecidos.dto.AtualizarStatusDTO;
import com.adoptme.api.registro_desaparecidos.dto.RegistroDesaparecidoRequestDTO;
import com.adoptme.api.registro_desaparecidos.dto.RegistroDesaparecidoResponseDTO;
import com.adoptme.api.registro_desaparecidos.enums.StatusDesaparecido;
import com.adoptme.api.registro_desaparecidos.mapper.RegistroDesaparecidoMapper;
import com.adoptme.api.registro_desaparecidos.service.RegistroDesaparecidoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros-desaparecidos")
@RequiredArgsConstructor
public class RegistroDesaparecidoController {

    private final RegistroDesaparecidoService service;
    private final RegistroDesaparecidoMapper mapper;

    @PostMapping
    @Operation(
            summary = "Criar registro de animal desaparecido",
            description = "Cadastra um alerta com as coordenadas geográficas do último avistamento."
    )
    public ResponseEntity<RegistroDesaparecidoResponseDTO> criar(
            @RequestParam Long usuarioId,
            @Valid @RequestBody RegistroDesaparecidoRequestDTO dto
    ) {
        RegistroDesaparecido entidade = mapper.toEntity(dto);
        RegistroDesaparecidoResponseDTO response = service.cadastrar(entidade, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os registros",
            description = "Filtra opcionalmente pelo status (DESAPARECIDO ou ENCONTRADO)."
    )
    public ResponseEntity<List<RegistroDesaparecidoResponseDTO>> listar(
            @RequestParam(required = false) StatusDesaparecido status
    ) {
        List<RegistroDesaparecidoResponseDTO> lista =
                (status != null) ? service.listarPorStatus(status) : service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDesaparecidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<RegistroDesaparecidoResponseDTO>> listarPorUsuario(
            @PathVariable Long usuarioId
    ) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RegistroDesaparecidoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusDTO dto
    ) {
        return ResponseEntity.ok(service.atualizarStatus(id, dto));
    }

    @PatchMapping("/{id}/coordenadas")
    public ResponseEntity<RegistroDesaparecidoResponseDTO> atualizarCoordenadas(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarCoordenadasDTO dto
    ) {
        return ResponseEntity.ok(service.atualizarCoordenadas(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}