package com.adoptme.api.formulario_adocao.model;

import com.adoptme.api.formulario_adocao.enums.StatusSolicitacao;
import com.adoptme.api.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "tb_formularioAdocao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FormularioAdocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Usuario usuario;

    //Aqui ficaria o atributo de animais, porém eu to esperando o caba fazer a classe.
    // Vai levar o ManyToOne também e o nullable = false gg ezz papai

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status = StatusSolicitacao.PENDENTE;

    private String justificativaStatus;

    @JoinColumn(nullable = false)
    private Boolean temOutrosAnimais;

    @JoinColumn(nullable = false)
    private String tipoResidencia;

    @JoinColumn(nullable = false)
    private LocalDateTime dataSolicitacao;

    @PrePersist
    protected void onCreate() {
        this.dataSolicitacao = LocalDateTime.now();
        this.status = StatusSolicitacao.PENDENTE;
    }

}
