package com.adoptme.api.formularioadocao.mapper;

import com.adoptme.api.formularioadocao.dto.FormularioAdocaoRequestDto;
import com.adoptme.api.formularioadocao.dto.FormularioAdocaoResponseDto;
import com.adoptme.api.formularioadocao.model.FormularioAdocao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FormularioAdocaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(source = "animalId", target = "animal.id")
    FormularioAdocao toEntity(FormularioAdocaoRequestDto dto);

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "usuario.nome", target = "usuarioNome")
    @Mapping(source = "animal.id", target = "animalId")
    @Mapping(source = "animal.nome", target = "animalNome")
    FormularioAdocaoResponseDto toDto(FormularioAdocao entity);
}
