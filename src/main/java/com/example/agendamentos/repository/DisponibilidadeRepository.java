package com.example.agendamentos.repository;

import com.example.agendamentos.entity.DiaDaSemana;
import com.example.agendamentos.entity.Disponibilidade;
import com.example.agendamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {
    List<Disponibilidade> findByProfissional(User profissional);
    List<Disponibilidade> findByProfissionalAndDiaDaSemana(User profissional, DiaDaSemana diaDaSemana);
}
