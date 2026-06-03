package com.adoptme.api.animal.repository;

import com.adoptme.api.animal.domain.Animal;
import com.adoptme.api.animal.enums.OrigemAnimal;
import com.adoptme.api.animal.enums.TagSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

  
    List<Animal> findByOrigemAndAdotadoFalse(OrigemAnimal origem);

   
    @Query("SELECT DISTINCT a FROM Animal a JOIN a.tagsSaude t WHERE t = :tag AND a.adotado = false")
    List<Animal> findByTagSaudeAndDisponiveis(@Param("tag") TagSaude tag);

    
    @Query("SELECT a FROM Animal a WHERE a.adotado = false ORDER BY a.dataEntradaAcolhimento ASC")
    List<Animal> findDisponiveisOrdenadosPorTempoEspera();

    
    @Query("SELECT a FROM Animal a WHERE a.especie = :especie AND a.adotado = false")
    List<Animal> findByEspecieAndDisponiveis(@Param("especie") String especie);
}
