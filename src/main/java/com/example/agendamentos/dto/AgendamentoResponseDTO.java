package com.example.agendamentos.dto;

import com.example.agendamentos.entity.Agendamento;
import com.example.agendamentos.entity.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id,
        String profissional,
        String servico,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        StatusAgendamento status
) {
    public AgendamentoResponseDTO(Agendamento agendamento){
        this(
                agendamento.getId(),
                agendamento.getProfissional().getName(),
                agendamento.getServico().getNome(),
                agendamento.getDataHoraInicio(),
                agendamento.getDataHoraFim(),
                agendamento.getStatus()
        );
    }
}
