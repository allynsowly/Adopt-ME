package com.adoptme.api.estabelecimento.domain;

import com.adoptme.api.usuario.domain.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_estabelecimentos")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEstabelecimento tipo;

    private Double latitude;

    private Double longitude;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private Boolean eh24hrs;

    @Column(nullable = false)
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Estabelecimento() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEstabelecimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEstabelecimento tipo) {
        this.tipo = tipo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getEh24hrs() {
        return eh24hrs;
    }

    public void setEh24hrs(Boolean eh24hrs) {
        this.eh24hrs = eh24hrs;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Transient
    public boolean isDestaqueVisual() {
        return tipo == TipoEstabelecimento.ONG;
    }
}