package com.adoptme.api.mapper;

import com.adoptme.api.usuario.domain.Usuario;
import com.adoptme.api.usuario.dto.UsuarioRequestDTO;
import com.adoptme.api.usuario.dto.UsuarioResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Converte o Request (o que o utilizador envia) para a Entidade da base de dados
    Usuario toEntity(UsuarioRequestDTO requestDTO);

    // Converte a Entidade da base de dados para o Response (o que o sistema devolve, sem a palavra-passe)
    UsuarioResponseDTO toResponseDTO(Usuario usuario);
}