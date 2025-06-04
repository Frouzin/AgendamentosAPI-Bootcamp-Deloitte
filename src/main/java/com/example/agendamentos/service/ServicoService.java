package com.example.agendamentos.service;

import com.example.agendamentos.dto.ServicoRequestDTO;
import com.example.agendamentos.dto.ServicoResponseDTO;
import com.example.agendamentos.entity.Servico;
import com.example.agendamentos.entity.TipoUsuario;
import com.example.agendamentos.entity.User;
import com.example.agendamentos.repository.ServicoRepository;
import com.example.agendamentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final UserRepository userRepository;

    public ServicoService(ServicoRepository servicoRepository, UserRepository userRepository) {
        this.servicoRepository = servicoRepository;
        this.userRepository = userRepository;
    }

    public ServicoResponseDTO criar(ServicoRequestDTO dto, User profissional) throws AccessDeniedException {
        if (!profissional.getTipoUsuario().equals(TipoUsuario.PROFISSIONAL)) {
            throw new AccessDeniedException("Apenas profissionais podem criar serviços.");
        }

        Servico servico = new Servico();
        servico.setNome(dto.nome());
        servico.setDescricao(dto.descricao());
        servico.setDuracaoEmMinutos(dto.duracaoEmMinutos());
        servico.setPreco(dto.preco());
        servico.setProfissional(profissional);

        servico = servicoRepository.save(servico);
        return new ServicoResponseDTO(servico);
    }

    public List<ServicoResponseDTO> listarDoProfissional(User profissional) {
        return servicoRepository.findByProfissional(profissional)
                .stream()
                .map(ServicoResponseDTO::new)
                .toList();
    }

    public List<ServicoResponseDTO> listarDoProfissionalId(Long profissionalId) {
        List<Servico> servicos = servicoRepository.findByProfissionalId(profissionalId);
        return servicos.stream()
                .map(ServicoResponseDTO::fromEntity)
                .toList();
    }

    public ServicoResponseDTO atualizar(Long id, ServicoRequestDTO dto, User profissional) throws AccessDeniedException {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        if (!servico.getProfissional().getId().equals(profissional.getId())) {
            throw new AccessDeniedException("Você não tem permissão para editar este serviço.");
        }

        servico.setNome(dto.nome());
        servico.setDescricao(dto.descricao());
        servico.setDuracaoEmMinutos(dto.duracaoEmMinutos());
        servico.setPreco(dto.preco());

        return new ServicoResponseDTO(servicoRepository.save(servico));
    }

    public void deletar(Long id, User profissional) throws AccessDeniedException {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        if (!servico.getProfissional().getId().equals(profissional.getId())) {
            throw new AccessDeniedException("Você não pode deletar este serviço.");
        }

        servicoRepository.delete(servico);
    }
}