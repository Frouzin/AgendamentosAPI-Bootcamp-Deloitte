package com.example.agendamentos.service;

import com.example.agendamentos.dto.AgendamentoRequestDTO;
import com.example.agendamentos.dto.AgendamentoResponseDTO;
import com.example.agendamentos.entity.*;
import com.example.agendamentos.repository.AgendamentoRepository;
import com.example.agendamentos.repository.ServicoRepository;
import com.example.agendamentos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UserRepository userRepository;
    private final ServicoRepository servicoRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository agendamentoRepository, UserRepository userRepository, ServicoRepository servicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.userRepository = userRepository;
        this.servicoRepository = servicoRepository;
    }

    public AgendamentoResponseDTO agendar(AgendamentoRequestDTO dto, User cliente) {
        Servico servico = servicoRepository.findById(dto.servicoId())
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));

        User profissional = userRepository.findById(dto.profissionalId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        if (!profissional.getTipoUsuario().equals(TipoUsuario.PROFISSIONAL)){
            throw new IllegalArgumentException("Usuário não é um profissional");
        }

        LocalDateTime inicio = dto.dataHoraInicio();
        LocalDateTime fim = inicio.plusMinutes(servico.getDuracaoEmMinutos());

        boolean conflito = agendamentoRepository.existsByProfissionalAndDataHoraInicioBetween(
                profissional, inicio, fim.minusSeconds(1));
        if (conflito) {
            throw new RuntimeException("Horário já ocupado pelo profissional");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);
        agendamento.setDataHoraInicio(inicio);
        agendamento.setDataHoraFim(fim);
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        return new AgendamentoResponseDTO(agendamentoRepository.save(agendamento));
    }
    public List<AgendamentoResponseDTO> listarDoCliente(User cliente) {
        return agendamentoRepository.findByCliente(cliente).stream()
                .map(AgendamentoResponseDTO::new).toList();
    }

    public void cancelarPorCliente(Long id, User cliente) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));

        if (!agendamento.getCliente().getId().equals(cliente.getId())) {
            throw new IllegalArgumentException("Você não pode cancelar este agendamento");
        }

        if (agendamento.getDataHoraInicio().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new RuntimeException("Só é possível cancelar com pelo menos 2 horas de antecedência");
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO_CLIENTE);
        agendamentoRepository.save(agendamento);
    }
}
