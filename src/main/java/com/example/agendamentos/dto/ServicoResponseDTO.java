package com.example.agendamentos.dto;

import com.example.agendamentos.entity.Servico;

import java.math.BigDecimal;

public record ServicoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Integer duracaoEmMinutos,
        BigDecimal preco
) {
    public ServicoResponseDTO(Servico servico){
        this(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getDuracaoEmMinutos(),
                servico.getPreco()
        );
    }
}
