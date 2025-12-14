package com.fightscene.backend.domain.user.service;

import com.fightscene.backend.domain.user.User;
import com.fightscene.backend.domain.user.UserRole;
import com.fightscene.backend.domain.user.repository.UserRepository;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;

    public User signup(String email, String passwordHash) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("Email already exists");
        }

        User user = User.builder()
                .email(email)
                .passwordHash(passwordHash)
                .role(UserRole.USER)
                .build();

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User login(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Invalid credentials"));
    }
}
