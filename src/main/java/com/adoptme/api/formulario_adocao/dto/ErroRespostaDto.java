package com.adoptme.api.formulario_adocao.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErroRespostaDto(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        String mensagem,
        List<String> camposComErro
) {
}
