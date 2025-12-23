package com.fightscene.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fightscene.backend.domain.user.User;
import com.fightscene.backend.domain.user.service.AuthService;
import com.fightscene.backend.dto.auth.AuthResponseDto;
import com.fightscene.backend.dto.auth.LoginRequestDto;
import com.fightscene.backend.dto.auth.SignupRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@Valid @RequestBody SignupRequestDto dto) {
        User user = authService.signup(dto.email(), dto.password());
        AuthResponseDto response = new AuthResponseDto(user.getUserId(), user.getRole(), null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto dto) {
        String token = authService.login(dto.email(), dto.password());
        AuthResponseDto response = new AuthResponseDto(null,null,token);
        return ResponseEntity.ok(response);
    }
}
