package com.example.agendamentos.service;

import com.example.agendamentos.dto.PasswordResetDTO;
import com.example.agendamentos.dto.UserRequestDTO;
import com.example.agendamentos.dto.UserResponseDTO;
import com.example.agendamentos.entity.User;
import com.example.agendamentos.mapper.UserMapper;
import com.example.agendamentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return userMapper.toDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setSenha(userRequestDTO.getSenha());
        user.setTipoUsuario(userRequestDTO.getTipoUsuario());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponseDTO resetPassword(PasswordResetDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com esse e-mail"));
        user.setSenha(dto.getNovaSenha());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }
}
