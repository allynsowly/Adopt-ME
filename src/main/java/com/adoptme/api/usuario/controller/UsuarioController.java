package com.adoptme.api.usuario.controller;


import com.adoptme.api.dto.UsuarioRequestDTO;
import com.adoptme.api.dto.UsuarioResponseDTO;
import com.adoptme.api.mapper.UsuarioMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.adoptme.api.usuario.domain.Usuario;
import com.adoptme.api.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping
    @Operation(summary = "Criar um novo usuário", description = "Recebe dados do usuário sem o ID e o salva no banco.")
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioRequestDTO requestDTO) {
        // Converte RequestDTO -> Entidade
        Usuario usuarioParaSalvar = usuarioMapper.toEntity(requestDTO);
        
        // Salva a Entidade
        Usuario usuarioSalvo = usuarioService.criarUsuario(usuarioParaSalvar);
        
        // Converte Entidade -> ResponseDTO
        UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(usuarioSalvo);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de usuários, ocultando a senha.")
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        
        // Converte a lista de Entidades para uma lista de ResponseDTOs
        List<UsuarioResponseDTO> responseList = usuarios.stream()
                .map(usuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
                
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico, ocultando a senha.")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        
        if (usuario.isPresent()) {
            UsuarioResponseDTO responseDTO = usuarioMapper.toResponseDTO(usuario.get());
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.notFound().build();
    }
}