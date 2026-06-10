package com.adoptme.api.formularioadocao.dto;

import jakarta.validation.constraints.NotBlank;

public record RejeicaoAdocaoDto(
        @NotBlank(message = "A justificativa da rejeição é obrigatória")
        String justificativa
) {
}
