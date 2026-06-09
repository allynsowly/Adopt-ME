package com.adoptme.api.mapper;

import com.adoptme.api.domain.Usuario;
import com.adoptme.api.dto.UsuarioRequestDTO;
import com.adoptme.api.dto.UsuarioResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Converte o Request (o que o utilizador envia) para a Entidade da base de dados
    Usuario toEntity(UsuarioRequestDTO requestDTO);

    // Converte a Entidade da base de dados para o Response (o que o sistema devolve, sem a palavra-passe)
    UsuarioResponseDTO toResponseDTO(Usuario usuario);
}