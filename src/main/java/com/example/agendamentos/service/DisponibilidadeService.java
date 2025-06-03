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

    public DisponibilidadeResponseDTO createDisponibilidade(DisponibilidadeRequestDTO dto, User profissional) throws AccessDeniedException {
        verificarPermissao(profissional);

        Disponibilidade disponibilidade = new Disponibilidade();
        disponibilidade.setDiaDaSemana(dto.diaDaSemana());
        disponibilidade.setHoraInicio(dto.horaInicio());
        disponibilidade.setHoraFim(dto.horaFim());
        disponibilidade.setProfissional(profissional);

        return new DisponibilidadeResponseDTO(disponibilidadeRepository.save(disponibilidade));
    }

    public List<DisponibilidadeResponseDTO> listarDisponibilidade(User profissional) throws AccessDeniedException {
        verificarPermissao(profissional);

        return disponibilidadeRepository.findByProfissional(profissional).stream()
                .map(DisponibilidadeResponseDTO::new)
                .toList();
    }

    public void deletarDisponibilidade(Long id, User profissional) throws AccessDeniedException {
        Disponibilidade d = disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada"));

        if (!d.getProfissional().getId().equals(profissional.getId())) {
            throw new AccessDeniedException("Não autorizado.");
        }

        disponibilidadeRepository.delete(d);
    }

    private void verificarPermissao(User user) throws AccessDeniedException {
        if (user.getTipoUsuario() != TipoUsuario.PROFISSIONAL) {
            throw new AccessDeniedException("Apenas profissionais podem gerenciar disponibilidade.");
        }
    }

}
