package com.example.agendamentos.controller;

import com.example.agendamentos.dto.LoginDTO;
import com.example.agendamentos.dto.UserRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDTO userRequestDTO){
        return "Usuário registrado com sucesso: " + userRequestDTO.getName();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return "Usuário logado com sucesso: " + loginDTO.getEmail();
    }
}
