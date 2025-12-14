package com.fightscene.backend.dto.fighter;

import java.util.Map;
import java.util.UUID;

import com.fightscene.backend.domain.fighter.Gender;
import com.fightscene.backend.domain.fighter.WeightClass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateFighterDto(
        @NotBlank @Size(max = 100) String firstName,
        @NotBlank @Size(max = 100) String lastName,
        @Size(max = 100) String nickname,
        @NotNull Gender gender,
        @NotNull WeightClass weightClass,
        @NotNull UUID gymId,
        @Size(max = 5000) String bio,
        @Size(max = 512) String profilePicUrl,
        @Size(max = 512) String tapologyProfileUrl,
        Map<String, String> externalLinks
) {}
