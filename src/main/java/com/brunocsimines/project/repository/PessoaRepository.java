package com.brunocsimines.project.repository;

import com.brunocsimines.project.domain.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoas, Long> {
    Pessoas findByCpf(String cpf);
}
