package com.adoptme.api.estabelecimento.controller;

import com.adoptme.api.estabelecimento.domain.TipoEstabelecimento;
import com.adoptme.api.estabelecimento.dto.EstabelecimentoRequestDTO;
import com.adoptme.api.estabelecimento.dto.EstabelecimentoResponseDTO;
import com.adoptme.api.estabelecimento.service.EstabelecimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
@Tag(name = "Estabelecimentos")
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    public EstabelecimentoController(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    @PostMapping
    @Operation(summary = "Criar estabelecimento")
    public ResponseEntity<EstabelecimentoResponseDTO> criar(@RequestBody EstabelecimentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estabelecimentoService.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os estabelecimentos")
    public ResponseEntity<List<EstabelecimentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(estabelecimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estabelecimento por id")
    public ResponseEntity<EstabelecimentoResponseDTO> buscarPorId(@PathVariable Long id) {
        EstabelecimentoResponseDTO estabelecimento = estabelecimentoService.buscarPorId(id);
        return estabelecimento != null ? ResponseEntity.ok(estabelecimento) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar estabelecimento")
    public ResponseEntity<EstabelecimentoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody EstabelecimentoRequestDTO dto) {
        return ResponseEntity.ok(estabelecimentoService.atualizar(id, dto));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar estabelecimento por nome")
    public ResponseEntity<List<EstabelecimentoResponseDTO>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(estabelecimentoService.buscarPorNome(nome));
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Buscar estabelecimento por tipo")
    public ResponseEntity<List<EstabelecimentoResponseDTO>> buscarPorTipo(@PathVariable TipoEstabelecimento tipo) {
        return ResponseEntity.ok(estabelecimentoService.buscarPorTipo(tipo));
    }

    @GetMapping("/destaques")
    @Operation(summary = "Listar estabelecimentos 24 horas")
    public ResponseEntity<List<EstabelecimentoResponseDTO>> listar24Horas() {
        return ResponseEntity.ok(estabelecimentoService.buscar24Horas());
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar estabelecimentos por usuário")
    public ResponseEntity<List<EstabelecimentoResponseDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(estabelecimentoService.buscarPorUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir estabelecimento")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estabelecimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}