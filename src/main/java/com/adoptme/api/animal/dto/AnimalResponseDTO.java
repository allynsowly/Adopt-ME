package com.adoptme.api.animal.dto;

import com.adoptme.api.animal.domain.Animal;
import com.adoptme.api.animal.enums.EspecieAnimal;
import com.adoptme.api.animal.enums.OrigemAnimal;
import com.adoptme.api.animal.enums.TagSaude;

import java.time.LocalDate;
import java.util.List;


public class AnimalResponseDTO {

    private Long id;
    private String nome;
    private String raca;
    private Integer idadeAnos;
    private EspecieAnimal especie;
    private LocalDate dataEntradaAcolhimento;
    private OrigemAnimal origem;
    private List<TagSaude> tagsSaude;
    private Boolean adotado;

    
    private long diasEmEspera;

    
    public AnimalResponseDTO(Animal animal) {
        this.id = animal.getId();
        this.nome = animal.getNome();
        this.raca = animal.getRaca();
        this.idadeAnos = animal.getIdadeAnos();
        this.especie = animal.getEspecie();
        this.dataEntradaAcolhimento = animal.getDataEntradaAcolhimento();
        this.origem = animal.getOrigem();
        this.tagsSaude = animal.getTagsSaude();
        this.adotado = animal.getAdotado();
        this.diasEmEspera = animal.calcularDiasEmEspera();
    }

   
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getRaca() { return raca; }
    public Integer getIdadeAnos() { return idadeAnos; }
    public EspecieAnimal getEspecie() { return especie; }
    public LocalDate getDataEntradaAcolhimento() { return dataEntradaAcolhimento; }
    public OrigemAnimal getOrigem() { return origem; }
    public List<TagSaude> getTagsSaude() { return tagsSaude; }
    public Boolean getAdotado() { return adotado; }
    public long getDiasEmEspera() { return diasEmEspera; }
}
