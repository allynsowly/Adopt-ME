package com.adoptme.api.estabelecimento.dto;

import com.adoptme.api.estabelecimento.domain.TipoEstabelecimento;

public class EstabelecimentoRequestDTO {
    private String nome;
    private TipoEstabelecimento tipo;
    private Double latitude;
    private Double longitude;
    private String endereco;
    private Boolean eh24hrs;
    private String telefone;
    private Long usuarioId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoEstabelecimento getTipo() { return tipo; }
    public void setTipo(TipoEstabelecimento tipo) { this.tipo = tipo; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Boolean getEh24hrs() { return eh24hrs; }
    public void setEh24hrs(Boolean eh24hrs) { this.eh24hrs = eh24hrs; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}