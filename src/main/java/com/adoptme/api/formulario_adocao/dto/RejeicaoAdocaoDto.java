package com.adoptme.api.formulario_adocao.dto;

import jakarta.validation.constraints.NotBlank;

public record RejeicaoAdocaoDto(
        @NotBlank(message = "A justificativa da rejeição é obrigatória")
        String justificativa
) {
}
