package com.example.agendamentos.repository;

import com.example.agendamentos.entity.Agendamento;
import com.example.agendamentos.entity.StatusAgendamento;
import com.example.agendamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByCliente(User cliente);

    List<Agendamento> findByProfissionalAndDataHoraInicioBetween(User profissional, LocalDateTime inicio, LocalDateTime fim);

    boolean existsByProfissionalAndDataHoraInicioBetween(User profissional, LocalDateTime inicio, LocalDateTime fim);

    Arrays findAllByStatus(StatusAgendamento statusAgendamento);
}
