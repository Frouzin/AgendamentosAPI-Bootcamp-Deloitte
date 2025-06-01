package com.example.agendamentos.repository;

import com.example.agendamentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
