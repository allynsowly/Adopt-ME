package com.adoptme.api.forum.controller;

import com.adoptme.api.forum.domain.ForumResposta;
import com.adoptme.api.forum.dto.ForumRespostaDTO;
import com.adoptme.api.forum.service.ForumRespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forum/respostas")
public class ForumRespostaController {

    @Autowired
    private ForumRespostaService forumRespostaService;

    @PostMapping
    public ResponseEntity<ForumResposta> criarResposta(
            @RequestBody ForumRespostaDTO dto) {

        ForumResposta resposta =
                forumRespostaService.criarResposta(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resposta);
    }
}