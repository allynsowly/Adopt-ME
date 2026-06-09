package com.adoptme.api.estabelecimento.controller;

import com.adoptme.api.estabelecimento.domain.Estabelecimento;
import com.adoptme.api.estabelecimento.domain.TipoEstabelecimento;
import com.adoptme.api.estabelecimento.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @PostMapping
    public ResponseEntity<Estabelecimento> criar(@RequestBody Estabelecimento estabelecimento) {
        Estabelecimento novo = estabelecimentoService.criar(estabelecimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<Estabelecimento>> listarTodos() {
        return ResponseEntity.ok(estabelecimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estabelecimento> buscarPorId(@PathVariable Long id) {
        Optional<Estabelecimento> estabelecimento = estabelecimentoService.buscarPorId(id);
        return estabelecimento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Estabelecimento>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(estabelecimentoService.buscarPorNome(nome));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Estabelecimento>> buscarPorTipo(@PathVariable TipoEstabelecimento tipo) {
        return ResponseEntity.ok(estabelecimentoService.buscarPorTipo(tipo));
    }

    @GetMapping("/destaques")
    public ResponseEntity<List<Estabelecimento>> listar24Horas() {
        return ResponseEntity.ok(estabelecimentoService.buscar24Horas());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Estabelecimento>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(estabelecimentoService.buscarPorUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estabelecimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}