package com.example.agendamentos.controller;

import com.example.agendamentos.dto.LoginDTO;
import com.example.agendamentos.dto.PasswordResetDTO;
import com.example.agendamentos.dto.UserRequestDTO;
import com.example.agendamentos.dto.UserResponseDTO;
import com.example.agendamentos.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO userRequestDTO){
        return userService.createUser(userRequestDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return "Usuário logado com sucesso: " + loginDTO.getEmail();
    }

    @PutMapping("/reset-password")
    public UserResponseDTO resetPassword(@RequestBody PasswordResetDTO passwordResetDTO) {
        return userService.resetPassword(passwordResetDTO);
    }
}
