package com.example.agendamentos.controller;

import com.example.agendamentos.dto.ServicoRequestDTO;
import com.example.agendamentos.dto.ServicoResponseDTO;
import com.example.agendamentos.entity.User;
import com.example.agendamentos.service.ServicoService;
import com.example.agendamentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/servicos")
@PreAuthorize("hasRole('PROFISSIONAL')")
public class ServicoController {

    private final ServicoService servicoService;
    private final UserService userService;

    public ServicoController(ServicoService servicoService, UserService userService) {
        this.servicoService = servicoService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> createServico(@RequestBody @Valid ServicoRequestDTO dto) throws AccessDeniedException {
        User profissional = userService.getAuthenticatedUser();
        return ResponseEntity.ok(servicoService.criar(dto, profissional));
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> getAllServico() {
        User profissional = userService.getAuthenticatedUser();
        return ResponseEntity.ok(servicoService.listarDoProfissional(profissional));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> updateServico(@PathVariable Long id, @RequestBody @Valid ServicoRequestDTO dto) throws AccessDeniedException {
        User profissional = userService.getAuthenticatedUser();
        return ResponseEntity.ok(servicoService.atualizar(id, dto, profissional));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) throws AccessDeniedException {
        User profissional = userService.getAuthenticatedUser();
        servicoService.deletar(id, profissional);
        return ResponseEntity.noContent().build();
    }
}
