package com.adoptme.api.registro_desaparecidos.mapper;

import com.adoptme.api.registro_desaparecidos.domain.RegistroDesaparecido;
import com.adoptme.api.registro_desaparecidos.dto.RegistroDesaparecidoRequestDTO;
import com.adoptme.api.registro_desaparecidos.dto.RegistroDesaparecidoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroDesaparecidoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true) // O Service busca o usuário no banco
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "dataDesaparecimento", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    RegistroDesaparecido toEntity(RegistroDesaparecidoRequestDTO dto);

    RegistroDesaparecidoResponseDTO toResponseDTO(RegistroDesaparecido entity);
}
