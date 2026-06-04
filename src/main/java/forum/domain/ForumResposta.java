package com.adoptme.api.forum.domain;

import com.adoptme.api.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_forum_respostas")
public class ForumResposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String conteudo;

    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @JsonIgnoreProperties({
            "perguntas",
            "respostas"
    })

    @ManyToOne
    @JoinColumn(name = "pergunta_id")
    private ForumPergunta pergunta;
    @JsonIgnoreProperties({
            "respostas"
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    })

    public ForumResposta() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public ForumPergunta getPergunta() { return pergunta; }
    public void setPergunta(ForumPergunta pergunta) { this.pergunta = pergunta; }
}