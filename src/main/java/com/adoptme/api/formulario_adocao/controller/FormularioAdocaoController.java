package com.adoptme.api.formulario_adocao.controller;

import com.adoptme.api.formulario_adocao.dto.FormularioAdocaoRequestDto;
import com.adoptme.api.formulario_adocao.dto.FormularioAdocaoResponseDto;
import com.adoptme.api.formulario_adocao.dto.RejeicaoAdocaoDto;
import com.adoptme.api.formulario_adocao.service.FormularioAdocaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/formulario-adocao")
@AllArgsConstructor
@RestController
public class FormularioAdocaoController {

    private final FormularioAdocaoService service;


    @PostMapping
    public ResponseEntity<FormularioAdocaoResponseDto> criarSolicitacao(@RequestBody @Valid FormularioAdocaoRequestDto dto) {
        FormularioAdocaoResponseDto response = service.criarSolicitacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<FormularioAdocaoResponseDto>> listarTodos() {
        List<FormularioAdocaoResponseDto> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FormularioAdocaoResponseDto> buscarPorId(@PathVariable Long id) {
        FormularioAdocaoResponseDto response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovarSolicitacao(@PathVariable Long id) {
        service.aprovarSolicitacao(id);
        return ResponseEntity.noContent().build(); 
    }


    @PatchMapping("/{id}/rejeitar")
    public ResponseEntity<Void> rejeitarSolicitacao(@PathVariable Long id, @RequestBody @Valid RejeicaoAdocaoDto dto) {
        service.rejeitarSolicitacao(id, dto.justificativa());
        return ResponseEntity.noContent().build();
    }
}
