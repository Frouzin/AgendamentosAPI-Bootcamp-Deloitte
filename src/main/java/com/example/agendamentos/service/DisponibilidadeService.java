package com.example.agendamentos.service;

import com.example.agendamentos.dto.DisponibilidadeRequestDTO;
import com.example.agendamentos.dto.DisponibilidadeResponseDTO;
import com.example.agendamentos.entity.Disponibilidade;
import com.example.agendamentos.entity.TipoUsuario;
import com.example.agendamentos.entity.User;
import com.example.agendamentos.repository.DisponibilidadeRepository;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository disponibilidadeRepository;
    private final UserService userService;

    public DisponibilidadeService(DisponibilidadeRepository disponibilidadeRepository, UserService userService) {
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.userService = userService;
    }

    public DisponibilidadeResponseDTO createDisponibilidade(DisponibilidadeRequestDTO dto) throws AccessDeniedException {

        Disponibilidade disponibilidade = new Disponibilidade();
        disponibilidade.setDiaDaSemana(dto.diaDaSemana());
        disponibilidade.setHoraInicio(dto.horaInicio());
        disponibilidade.setHoraFim(dto.horaFim());

        return new DisponibilidadeResponseDTO(disponibilidadeRepository.save(disponibilidade));
    }

    public List<DisponibilidadeResponseDTO> listarDisponibilidade() throws AccessDeniedException {

        return disponibilidadeRepository.findAll().stream()
                .map(DisponibilidadeResponseDTO::new)
                .toList();
    }

    public void deletarDisponibilidade(Long id) throws AccessDeniedException {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada"));
        disponibilidadeRepository.delete(disponibilidade);
    }
}
