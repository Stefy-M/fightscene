package com.fightscene.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupRequestDto(
        @NotBlank @Email String email,
        @NotBlank String password
) {}
