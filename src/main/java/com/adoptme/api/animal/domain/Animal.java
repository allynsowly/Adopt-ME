package com.adoptme.api.animal.domain;

import com.adoptme.api.animal.enums.EspecieAnimal;
import com.adoptme.api.animal.enums.OrigemAnimal;
import com.adoptme.api.animal.enums.TagSaude;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "tb_animais")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String raca;

    @Column(nullable = false)
    private Integer idadeAnos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EspecieAnimal especie;

    @Column(nullable = false)
    private LocalDate dataEntradaAcolhimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigemAnimal origem;

    @ElementCollection
    @CollectionTable(name = "tb_animal_tags_saude", joinColumns = @JoinColumn(name = "animal_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "tag")
    private List<TagSaude> tagsSaude;

    @Column(nullable = false)
    private Boolean adotado = false;

   
    public Animal() {}

    
    public long calcularDiasEmEspera() {
        return ChronoUnit.DAYS.between(dataEntradaAcolhimento, LocalDate.now());
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public Integer getIdadeAnos() { return idadeAnos; }
    public void setIdadeAnos(Integer idadeAnos) { this.idadeAnos = idadeAnos; }

    public EspecieAnimal getEspecie() { return especie; }
    public void setEspecie(EspecieAnimal especie) { this.especie = especie; }

    public LocalDate getDataEntradaAcolhimento() { return dataEntradaAcolhimento; }
    public void setDataEntradaAcolhimento(LocalDate dataEntradaAcolhimento) {
        this.dataEntradaAcolhimento = dataEntradaAcolhimento;
    }

    public OrigemAnimal getOrigem() { return origem; }
    public void setOrigem(OrigemAnimal origem) { this.origem = origem; }

    public List<TagSaude> getTagsSaude() { return tagsSaude; }
    public void setTagsSaude(List<TagSaude> tagsSaude) { this.tagsSaude = tagsSaude; }

    public Boolean getAdotado() { return adotado; }
    public void setAdotado(Boolean adotado) { this.adotado = adotado; }
}
