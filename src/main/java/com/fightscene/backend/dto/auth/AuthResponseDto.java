package com.fightscene.backend.dto.auth;

import java.util.UUID;

import com.fightscene.backend.domain.user.UserRole;

public record AuthResponseDto(
        UUID userId,
        UserRole role,
        String token
) {}
