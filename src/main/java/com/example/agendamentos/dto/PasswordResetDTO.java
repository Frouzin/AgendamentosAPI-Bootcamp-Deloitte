package com.example.agendamentos.dto;

public class PasswordResetDTO {
    private String email;
    private String novaSenha;

    // getters e setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNovaSenha() { return novaSenha; }
    public void setNovaSenha(String novaSenha) { this.novaSenha = novaSenha; }
}
