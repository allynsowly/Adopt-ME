package com.adoptme.api.registro_desaparecidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegistroDesaparecidoRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "O nome do animal não pode estar em branco")
    private String nomeAnimal;

    private String descricaoCaracteristicas;

    @NotNull(message = "A latitude é obrigatória")
    private Double ultimaLatitude;

    @NotNull(message = "A longitude é obrigatória")
    private Double ultimaLongitude;

    // Getters e Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getNomeAnimal() { return nomeAnimal; }
    public void setNomeAnimal(String nomeAnimal) { this.nomeAnimal = nomeAnimal; }
    public String getDescricaoCaracteristicas() { return descricaoCaracteristicas; }
    public void setDescricaoCaracteristicas(String descricaoCaracteristicas) { this.descricaoCaracteristicas = descricaoCaracteristicas; }
    public Double getUltimaLatitude() { return ultimaLatitude; }
    public void setUltimaLatitude(Double ultimaLatitude) { this.ultimaLatitude = ultimaLatitude; }
    public Double getUltimaLongitude() { return ultimaLongitude; }
    public void setUltimaLongitude(Double ultimaLongitude) { this.ultimaLongitude = ultimaLongitude; }
}