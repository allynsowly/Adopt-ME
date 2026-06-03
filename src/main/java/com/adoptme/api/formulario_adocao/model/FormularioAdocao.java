package com.adoptme.api.formulario_adocao.model;

import com.adoptme.api.animal.domain.Animal;
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
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSolicitacao status = StatusSolicitacao.PENDENTE;

    @Column(name = "justificativa_status", length = 500)
    private String justificativaStatus;

    // --- Respostas do Formulário de Adoção (Campos de triagem) ---
    @Column(name = "motivo_adocao", nullable = false, columnDefinition = "TEXT")
    private String motivoAdocao;

    @Column(name = "tem_outros_animais", nullable = false)
    private Boolean temOutrosAnimais;

    @Column(name = "tipo_residencia", nullable = false)
    private String tipoResidencia;

    // --- Auditoria Automática ---
    @Column(name = "data_solicitacao", nullable = false)
    private LocalDateTime dataSolicitacao;

    // O gatilho que você aprendeu! Roda automático antes do INSERT no banco
    @PrePersist
    protected void onCreate() {
        this.dataSolicitacao = LocalDateTime.now();
        this.status = StatusSolicitacao.PENDENTE; // Garante o estado inicial seguro
    }

}
