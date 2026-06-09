package com.adoptme.api.estabelecimento.repository;

import com.adoptme.api.estabelecimento.domain.Estabelecimento;
import com.adoptme.api.estabelecimento.domain.TipoEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

    List<Estabelecimento> findByNomeContainingIgnoreCase(String nome);

    List<Estabelecimento> findByTipo(TipoEstabelecimento tipo);

    List<Estabelecimento> findByEh24hrs(Boolean eh24hrs);

    List<Estabelecimento> findByUsuarioId(Long usuarioId);
}