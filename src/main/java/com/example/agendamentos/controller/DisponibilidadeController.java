package com.example.agendamentos.controller;

import com.example.agendamentos.dto.DisponibilidadeRequestDTO;
import com.example.agendamentos.dto.DisponibilidadeResponseDTO;
import com.example.agendamentos.entity.User;
import com.example.agendamentos.service.DisponibilidadeService;
import com.example.agendamentos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibilidadeController {

    private final DisponibilidadeService disponibilidadeService;
    private final UserService userService;

    public DisponibilidadeController(DisponibilidadeService disponibilidadeService, UserService userService){
        this.disponibilidadeService = disponibilidadeService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<DisponibilidadeResponseDTO> createDisponibilidade(@RequestBody @Valid DisponibilidadeRequestDTO dto) throws AccessDeniedException {
        return ResponseEntity.ok(disponibilidadeService.createDisponibilidade(dto));
    }

    @GetMapping
    public ResponseEntity<List<DisponibilidadeResponseDTO>> getAllDisponibilidades() throws AccessDeniedException {
        return ResponseEntity.ok(disponibilidadeService.listarDisponibilidade());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilidade(@PathVariable Long id) throws AccessDeniedException {
        disponibilidadeService.deletarDisponibilidade(id);
        return ResponseEntity.noContent().build();
    }

}
