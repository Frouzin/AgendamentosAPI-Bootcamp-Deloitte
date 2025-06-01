package com.example.agendamentos.dto;

import com.example.agendamentos.entity.DiaDaSemana;

import java.time.LocalTime;

public record DisponibilidadeRequestDTO(
        DiaDaSemana diaDaSemana,
        LocalTime horaInicio,
        LocalTime horaFim

) {

}
