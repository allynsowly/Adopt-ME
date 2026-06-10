package com.adoptme.api.animal.dto;

import com.adoptme.api.animal.enums.EspecieAnimal;
import com.adoptme.api.animal.enums.OrigemAnimal;
import com.adoptme.api.animal.enums.TagSaude;

import java.time.LocalDate;
import java.util.List;


public class AnimalRequestDTO {

    private String nome;
    private String raca;
    private Integer idadeAnos;
    private EspecieAnimal especie;
    private LocalDate dataEntradaAcolhimento;
    private OrigemAnimal origem;
    private List<TagSaude> tagsSaude;
    private Boolean adotado;

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
