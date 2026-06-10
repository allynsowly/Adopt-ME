package com.adoptme.api.forum.controller;

import com.adoptme.api.forum.domain.ForumPergunta;
import com.adoptme.api.forum.dto.ForumPerguntaDTO;
import com.adoptme.api.forum.service.ForumPerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forum/perguntas")
public class ForumPerguntaController {

    @Autowired
    private ForumPerguntaService forumPerguntaService;

    @PostMapping
    public ResponseEntity<ForumPergunta> criarPergunta(
            @RequestBody ForumPerguntaDTO dto) {

        ForumPergunta pergunta =
                forumPerguntaService.criarPergunta(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pergunta);
    }

    @GetMapping
    public ResponseEntity<List<ForumPergunta>> listarTodas() {
        return ResponseEntity.ok(
                forumPerguntaService.listarTodas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumPergunta> buscarPorId(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    forumPerguntaService.buscarPorId(id)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}