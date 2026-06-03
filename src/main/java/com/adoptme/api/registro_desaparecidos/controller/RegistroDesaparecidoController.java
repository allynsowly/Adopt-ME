package com.adoptme.api.registro_desaparecidos.controller;

import com.adoptme.api.registro_desaparecidos.domain.RegistroDesaparecido;
import com.adoptme.api.registro_desaparecidos.dto.AtualizarCoordenadasDTO;
import com.adoptme.api.registro_desaparecidos.dto.AtualizarStatusDTO;
import com.adoptme.api.registro_desaparecidos.dto.RegistroDesaparecidoResponseDTO;
import com.adoptme.api.registro_desaparecidos.enums.StatusDesaparecido;
import com.adoptme.api.registro_desaparecidos.service.RegistroDesaparecidoService;
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

    // ── POST /api/registros-desaparecidos ─────────────────────────────────────
    @PostMapping
    public ResponseEntity<RegistroDesaparecidoResponseDTO> criar(
            @RequestParam Long usuarioId,
            @Valid @RequestBody RegistroDesaparecido dados
    ) {
        // Ajustado para chamar 'cadastrar' passando os dados e o ID, conforme seu Service
        RegistroDesaparecidoResponseDTO response = service.cadastrar(dados, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ── GET /api/registros-desaparecidos ──────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<RegistroDesaparecidoResponseDTO>> listar(
            @RequestParam(required = false) StatusDesaparecido status
    ) {
        List<RegistroDesaparecidoResponseDTO> lista = (status != null)
                ? service.listarPorStatus(status)
                : service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // ── GET /api/registros-desaparecidos/{id} ─────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<RegistroDesaparecidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ── GET /api/registros-desaparecidos/usuario/{usuarioId} ──────────────────
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<RegistroDesaparecidoResponseDTO>> listarPorUsuario(
            @PathVariable Long usuarioId
    ) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    // ── PATCH /api/registros-desaparecidos/{id}/status ────────────────────────
    @PatchMapping("/{id}/status")
    public ResponseEntity<RegistroDesaparecidoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusDTO dto
    ) {
        return ResponseEntity.ok(service.atualizarStatus(id, dto));
    }

    // ── PATCH /api/registros-desaparecidos/{id}/coordenadas ───────────────────
    @PatchMapping("/{id}/coordenadas")
    public ResponseEntity<RegistroDesaparecidoResponseDTO> atualizarCoordenadas(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarCoordenadasDTO dto
    ) {
        return ResponseEntity.ok(service.atualizarCoordenadas(id, dto));
    }

    // ── DELETE /api/registros-desaparecidos/{id} ──────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}