package com.adoptme.api.registro_desaparecidos.dto;

import com.adoptme.api.registro_desaparecidos.enums.StatusDesaparecido;

public class AtualizarStatusDTO {

    private StatusDesaparecido status;

    public AtualizarStatusDTO() {}

    public StatusDesaparecido getStatus() { return status; }
    public void setStatus(StatusDesaparecido status) { this.status = status; }
}
