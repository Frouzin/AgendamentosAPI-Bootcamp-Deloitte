package com.example.agendamentos.mapper;

import com.example.agendamentos.dto.UserRequestDTO;
import com.example.agendamentos.dto.UserResponseDTO;
import com.example.agendamentos.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());
        user.setTipoUsuario(dto.getTipoUsuario());
        return user;
    }

    public UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setTipoUsuario(user.getTipoUsuario());
        return dto;
    }
}
