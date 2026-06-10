package com.adoptme.api.registrodesaparecidos.domain;

import com.adoptme.api.registrodesaparecidos.enums.StatusDesaparecido;
import com.adoptme.api.usuario.domain.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_registro_desaparecidos")
public class RegistroDesaparecido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String nomeAnimal;

    @Column(columnDefinition = "TEXT")
    private String descricaoCaracteristicas;


    @Column(nullable = false)
    private Double ultimaLatitude;

    @Column(nullable = false)
    private Double ultimaLongitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDesaparecido status;

    @Column(nullable = false)
    private LocalDate dataDesaparecimento;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    // Construtor vazio exigido pelo JPA
    public RegistroDesaparecido() {}

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
        if (this.status == null) {
            this.status = StatusDesaparecido.DESAPARECIDO;
        }
    }


    public void marcarComoEncontrado() {
        this.status = StatusDesaparecido.ENCONTRADO;
    }

    public void marcarComoDesaparecido() {
        this.status = StatusDesaparecido.DESAPARECIDO;
    }

    // Atualiza coordenadas com validação de range geográfico
    public void atualizarCoordenadas(Double latitude, Double longitude) {
        if (latitude == null || latitude < -90.0 || latitude > 90.0) {
            throw new RuntimeException("Latitude inválida: " + latitude + ". Deve ser entre -90.0 e 90.0");
        }
        if (longitude == null || longitude < -180.0 || longitude > 180.0) {
            throw new RuntimeException("Longitude inválida: " + longitude + ". Deve ser entre -180.0 e 180.0");
        }
        this.ultimaLatitude = latitude;
        this.ultimaLongitude = longitude;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNomeAnimal() { return nomeAnimal; }
    public void setNomeAnimal(String nomeAnimal) { this.nomeAnimal = nomeAnimal; }

    public String getDescricaoCaracteristicas() { return descricaoCaracteristicas; }
    public void setDescricaoCaracteristicas(String descricaoCaracteristicas) {
        this.descricaoCaracteristicas = descricaoCaracteristicas;
    }

    public Double getUltimaLatitude() { return ultimaLatitude; }
    public void setUltimaLatitude(Double ultimaLatitude) { this.ultimaLatitude = ultimaLatitude; }

    public Double getUltimaLongitude() { return ultimaLongitude; }
    public void setUltimaLongitude(Double ultimaLongitude) { this.ultimaLongitude = ultimaLongitude; }

    public StatusDesaparecido getStatus() { return status; }
    public void setStatus(StatusDesaparecido status) { this.status = status; }

    public LocalDate getDataDesaparecimento() { return dataDesaparecimento; }
    public void setDataDesaparecimento(LocalDate dataDesaparecimento) {
        this.dataDesaparecimento = dataDesaparecimento;
    }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
