package com.example.agendamentos.controller;

import com.example.agendamentos.dto.ServicoRequestDTO;
import com.example.agendamentos.dto.ServicoResponseDTO;
import com.example.agendamentos.entity.User;
import com.example.agendamentos.service.ServicoService;
import com.example.agendamentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;
    private final UserService userService;

    public ServicoController(ServicoService servicoService, UserService userService) {
        this.servicoService = servicoService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> createServico(
            @RequestBody @Valid ServicoRequestDTO dto,
            @RequestParam Long profissionalId
    ) throws AccessDeniedException {
        User profissional = userService.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        return ResponseEntity.ok(servicoService.criar(dto, profissional));
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> getAllServico(
            @RequestParam Long profissionalId
    ) {
        User profissional = userService.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        return ResponseEntity.ok(servicoService.listarDoProfissional(profissional));
    }

    @GetMapping("/publicos/{profissionalId}")
    public List<ServicoResponseDTO> getServicosByProfissional(@PathVariable Long profissionalId) {
        return servicoService.listarDoProfissionalId(profissionalId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> updateServico(
            @PathVariable Long id,
            @RequestBody @Valid ServicoRequestDTO dto,
            @RequestParam Long profissionalId
    ) throws AccessDeniedException {
        User profissional = userService.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        return ResponseEntity.ok(servicoService.atualizar(id, dto, profissional));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(
            @PathVariable Long id,
            @RequestParam Long profissionalId
    ) throws AccessDeniedException {
        User profissional = userService.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        servicoService.deletar(id, profissional);
        return ResponseEntity.noContent().build();
    }
}