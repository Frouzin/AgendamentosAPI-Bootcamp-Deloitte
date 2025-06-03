package com.example.agendamentos.dto;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        Long profissionalId,
        Long servicoId,
        LocalDateTime dataHoraInicio
) {
}
