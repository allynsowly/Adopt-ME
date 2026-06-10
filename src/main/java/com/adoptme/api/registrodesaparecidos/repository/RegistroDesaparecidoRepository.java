package com.adoptme.api.registrodesaparecidos.repository;

import com.adoptme.api.registrodesaparecidos.domain.RegistroDesaparecido;
import com.adoptme.api.registrodesaparecidos.enums.StatusDesaparecido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroDesaparecidoRepository extends JpaRepository<RegistroDesaparecido, Long> {

    // Busca todos os registros de um usuário
    List<RegistroDesaparecido> findByUsuarioId(Long usuarioId);

    // Filtra por status (DESAPARECIDO ou ENCONTRADO)
    List<RegistroDesaparecido> findByStatus(StatusDesaparecido status);

    // Filtra registros de um usuário por status
    List<RegistroDesaparecido> findByUsuarioIdAndStatus(Long usuarioId, StatusDesaparecido status);
}
