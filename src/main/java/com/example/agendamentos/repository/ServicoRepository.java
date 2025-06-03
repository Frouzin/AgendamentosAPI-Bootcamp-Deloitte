package com.example.agendamentos.repository;

import com.example.agendamentos.entity.Servico;
import com.example.agendamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByProfissional(User profissional);
    List<Servico> findByProfissionalId(Long profissionalId);
}
