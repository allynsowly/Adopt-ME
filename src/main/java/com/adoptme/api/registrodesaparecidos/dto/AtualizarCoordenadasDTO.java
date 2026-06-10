package com.adoptme.api.registrodesaparecidos.dto;

public class AtualizarCoordenadasDTO {

    private Double ultimaLatitude;
    private Double ultimaLongitude;

    public AtualizarCoordenadasDTO() {}

    public Double getUltimaLatitude() { return ultimaLatitude; }
    public void setUltimaLatitude(Double ultimaLatitude) { this.ultimaLatitude = ultimaLatitude; }

    public Double getUltimaLongitude() { return ultimaLongitude; }
    public void setUltimaLongitude(Double ultimaLongitude) { this.ultimaLongitude = ultimaLongitude; }
}
