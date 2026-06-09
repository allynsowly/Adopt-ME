package com.adoptme.api.formulario_adocao.repository;

import com.adoptme.api.formulario_adocao.model.FormularioAdocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioAdocaoRepository extends JpaRepository<FormularioAdocao, Long> {
}
