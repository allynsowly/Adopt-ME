package com.adoptme.api.forum.service;

import com.adoptme.api.forum.domain.ForumPergunta;
import com.adoptme.api.forum.domain.ForumResposta;
import com.adoptme.api.forum.dto.ForumRespostaDTO;
import com.adoptme.api.forum.repository.ForumPerguntaRepository;
import com.adoptme.api.forum.repository.ForumRespostaRepository;
import com.adoptme.api.usuario.domain.Usuario;
import com.adoptme.api.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ForumRespostaService {

    @Autowired
    private ForumRespostaRepository respostaRepository;

    @Autowired
    private ForumPerguntaRepository perguntaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ForumResposta criarResposta(ForumRespostaDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ForumPergunta pergunta = perguntaRepository.findById(dto.getPerguntaId())
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));

        ForumResposta resposta = new ForumResposta();

        resposta.setConteudo(dto.getConteudo());
        resposta.setDataCriacao(LocalDateTime.now());
        resposta.setUsuario(usuario);
        resposta.setPergunta(pergunta);

        return respostaRepository.save(resposta);
    }
}