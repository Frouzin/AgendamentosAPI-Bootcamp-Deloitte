package com.example.agendamentos.controller;

import com.example.agendamentos.dto.AgendamentoRequestDTO;
import com.example.agendamentos.dto.AgendamentoResponseDTO;
import com.example.agendamentos.entity.User;
import com.example.agendamentos.service.AgendamentoService;
import com.example.agendamentos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@PreAuthorize("hasRole('CLIENTE')")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final UserService userService;

    public AgendamentoController(AgendamentoService agendamentoService, UserService userService) {
        this.agendamentoService = agendamentoService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> agendar(@RequestBody @Valid AgendamentoRequestDTO dto) {
        User cliente = userService.getAuthenticatedUser();
        return ResponseEntity.ok(agendamentoService.agendar(dto, cliente));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listar(){
        User cliente = userService.getAuthenticatedUser();
        return ResponseEntity.ok(agendamentoService.listarDoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        User cliente = userService.getAuthenticatedUser();
        agendamentoService.cancelarPorCliente(id, cliente);
        return ResponseEntity.noContent().build();
    }
}
