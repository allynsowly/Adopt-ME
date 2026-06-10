package com.adoptme.api.registrodesaparecidos.dto;

import com.adoptme.api.registrodesaparecidos.enums.StatusDesaparecido;

public class AtualizarStatusDTO {

    private StatusDesaparecido status;

    public AtualizarStatusDTO() {}

    public StatusDesaparecido getStatus() { return status; }
    public void setStatus(StatusDesaparecido status) { this.status = status; }
}
