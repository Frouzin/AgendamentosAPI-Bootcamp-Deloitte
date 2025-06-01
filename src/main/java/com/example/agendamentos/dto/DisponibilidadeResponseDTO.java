package com.example.agendamentos.dto;

import com.example.agendamentos.entity.DiaDaSemana;
import com.example.agendamentos.entity.Disponibilidade;

public record DisponibilidadeResponseDTO(
        Long id,
        DiaDaSemana diaDaSemana,
        String horaInicio,
        String horaFim
) {
    public DisponibilidadeResponseDTO(Disponibilidade disponibilidade){
        this(
                disponibilidade.getId(),
                disponibilidade.getDiaDaSemana(),
                disponibilidade.getHoraInicio().toString(),
                disponibilidade.getHoraFim().toString()
        );
    }
}
