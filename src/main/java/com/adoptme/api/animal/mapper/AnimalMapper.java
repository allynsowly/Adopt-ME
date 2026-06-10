package com.adoptme.api.animal.mapper;

import com.adoptme.api.animal.domain.Animal;
import com.adoptme.api.animal.dto.AnimalRequestDTO;
import com.adoptme.api.animal.dto.AnimalResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AnimalMapper {

    
    @Mapping(target = "id", ignore = true)
    Animal toEntity(AnimalRequestDTO dto);

   
    @Mapping(expression = "java(entity.calcularDiasEmEspera())", target = "diasEmEspera")
    AnimalResponseDTO toDto(Animal entity);

    List<AnimalResponseDTO> toDtoList(List<Animal> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(AnimalRequestDTO dto, @MappingTarget Animal entity);
}
