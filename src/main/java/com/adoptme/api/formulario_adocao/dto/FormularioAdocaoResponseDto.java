package com.adoptme.api.formulario_adocao.dto;

import com.adoptme.api.formulario_adocao.enums.StatusSolicitacao;

import java.time.LocalDateTime;

public record FormularioAdocaoResponseDto(
        Long id,
        Long usuarioId,
        String usuarioNome,
        Long animalId,
        String animalNome,
        StatusSolicitacao status,
        String justificativaStatus,
        String motivoAdocao,
        Boolean temOutrosAnimais,
        String tipoResidencia,
        LocalDateTime dataSolicitacao
) {
}
