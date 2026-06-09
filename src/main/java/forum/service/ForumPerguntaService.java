package com.adoptme.api.forum.service;

import com.adoptme.api.animal.domain.Animal;
import com.adoptme.api.animal.repository.AnimalRepository;
import com.adoptme.api.forum.domain.ForumPergunta;
import com.adoptme.api.forum.dto.ForumPerguntaDTO;
import com.adoptme.api.forum.repository.ForumPerguntaRepository;
import com.adoptme.api.usuario.domain.Usuario;
import com.adoptme.api.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ForumPerguntaService {

    @Autowired
    private ForumPerguntaRepository perguntaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public ForumPergunta criarPergunta(ForumPerguntaDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        ForumPergunta pergunta = new ForumPergunta();

        pergunta.setTitulo(dto.getTitulo());
        pergunta.setDescricao(dto.getDescricao());
        pergunta.setDataCriacao(LocalDateTime.now());
        pergunta.setUsuario(usuario);
        pergunta.setAnimal(animal);

        return perguntaRepository.save(pergunta);
    }

    public List<ForumPergunta> listarTodas() {
        return perguntaRepository.findAll();
    }

    public ForumPergunta buscarPorId(Long id) {
        return perguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
    }
}