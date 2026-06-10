package com.adoptme.api.forum.repository;

import com.adoptme.api.forum.domain.ForumPergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumPerguntaRepository extends JpaRepository<ForumPergunta, Long> {
}