package com.adoptme.api.registro_desaparecidos.dto;

import com.adoptme.api.registro_desaparecidos.domain.RegistroDesaparecido;
import com.adoptme.api.registro_desaparecidos.enums.StatusDesaparecido;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistroDesaparecidoResponseDTO {

    private Long id;
    private Long usuarioId;
    private String nomeAnimal;
    private String descricaoCaracteristicas;
    private Double ultimaLatitude;
    private Double ultimaLongitude;
    private StatusDesaparecido status;
    private LocalDate dataDesaparecimento;
    private LocalDateTime criadoEm;

    public RegistroDesaparecidoResponseDTO(RegistroDesaparecido registro) {
        this.id = registro.getId();
        this.usuarioId = registro.getUsuario().getId();
        this.nomeAnimal = registro.getNomeAnimal();
        this.descricaoCaracteristicas = registro.getDescricaoCaracteristicas();
        this.ultimaLatitude = registro.getUltimaLatitude();
        this.ultimaLongitude = registro.getUltimaLongitude();
        this.status = registro.getStatus();
        this.dataDesaparecimento = registro.getDataDesaparecimento();
        this.criadoEm = registro.getCriadoEm();
    }

    // Getters
    public Long getId() { return id; }
    public Long getUsuarioId() { return usuarioId; }
    public String getNomeAnimal() { return nomeAnimal; }
    public String getDescricaoCaracteristicas() { return descricaoCaracteristicas; }
    public Double getUltimaLatitude() { return ultimaLatitude; }
    public Double getUltimaLongitude() { return ultimaLongitude; }
    public StatusDesaparecido getStatus() { return status; }
    public LocalDate getDataDesaparecimento() { return dataDesaparecimento; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
}