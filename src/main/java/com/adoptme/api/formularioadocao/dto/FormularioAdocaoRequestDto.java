package com.adoptme.api.formularioadocao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FormularioAdocaoRequestDto(
        @NotNull(message = "O ID do usuário é obrigatório")
        Long usuarioId,

        @NotNull(message = "O ID do animal é obrigatório")
        Long animalId,

        @NotBlank(message = "O motivo da adoção não pode estar em branco")
        String motivoAdocao,

        @NotNull(message = "Informe se possui outros animais")
        Boolean temOutrosAnimais,

        @NotBlank(message = "O tipo de residência é obrigatório")
        String tipoResidencia
) {
}
