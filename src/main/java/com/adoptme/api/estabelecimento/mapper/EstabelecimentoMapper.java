package com.adoptme.api.estabelecimento.mapper;

import com.adoptme.api.estabelecimento.domain.Estabelecimento;
import com.adoptme.api.estabelecimento.dto.EstabelecimentoRequestDTO;
import com.adoptme.api.estabelecimento.dto.EstabelecimentoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstabelecimentoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "usuarioId", target = "usuario.id")
    Estabelecimento toEntity(EstabelecimentoRequestDTO dto);

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "usuario.nome", target = "usuarioNome")
    @Mapping(expression = "java(entity.isDestaqueVisual())", target = "destaqueVisual")
    EstabelecimentoResponseDTO toDto(Estabelecimento entity);

    List<EstabelecimentoResponseDTO> toDtoList(List<Estabelecimento> entities);
}