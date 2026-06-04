package com.adoptme.api.forum.repository;

import com.adoptme.api.forum.domain.ForumResposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRespostaRepository extends JpaRepository<ForumResposta, Long> {
}