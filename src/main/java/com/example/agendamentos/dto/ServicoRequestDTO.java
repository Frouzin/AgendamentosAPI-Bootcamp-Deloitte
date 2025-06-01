package com.example.agendamentos.dto;

import java.math.BigDecimal;

public record ServicoRequestDTO(
    String nome,
    String descricao,
    Integer duracaoEmMinutos,
    BigDecimal preco
) {}
