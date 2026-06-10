package com.adoptme.api.formularioadocao.repository;

import com.adoptme.api.formularioadocao.model.FormularioAdocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormularioAdocaoRepository
        extends JpaRepository<FormularioAdocao, Long> {

    List<FormularioAdocao> findByAnimalId(Long animalId);
}
