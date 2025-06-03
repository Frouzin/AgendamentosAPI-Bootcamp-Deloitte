package com.example.agendamentos.controller;

import com.example.agendamentos.dto.DisponibilidadeRequestDTO;
import com.example.agendamentos.dto.DisponibilidadeResponseDTO;
import com.example.agendamentos.entity.User;
import com.example.agendamentos.service.DisponibilidadeService;
import com.example.agendamentos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
@PreAuthorize("hasRole('PROFISSIONAL')")
public class DisponibilidadeController {

    private final DisponibilidadeService disponibilidadeService;
    private final UserService userService;

    public DisponibilidadeController(DisponibilidadeService disponibilidadeService, UserService userService){
        this.disponibilidadeService = disponibilidadeService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<DisponibilidadeResponseDTO> createDisponibilidade(@RequestBody @Valid DisponibilidadeRequestDTO dto) throws AccessDeniedException {
        User profissional = userService.getAuthenticatedUser();
        return ResponseEntity.ok(disponibilidadeService.createDisponibilidade(dto, profissional));
    }

    @GetMapping
    public ResponseEntity<List<DisponibilidadeResponseDTO>> getAllDisponibilidades() throws AccessDeniedException {
        User profissional = userService.getAuthenticatedUser();
        return ResponseEntity.ok(disponibilidadeService.listarDisponibilidade(profissional));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilidade(@PathVariable Long id) throws AccessDeniedException {
        User profissional = userService.getAuthenticatedUser();
        disponibilidadeService.deletarDisponibilidade(id, profissional);
        return ResponseEntity.noContent().build();
    }

}
