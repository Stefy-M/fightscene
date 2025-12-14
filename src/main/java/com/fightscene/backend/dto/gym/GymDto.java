package com.fightscene.backend.dto.gym;

import java.util.UUID;

public record GymDto(
        UUID gymId,
        String name,
        String location
) {}
