package com.fightscene.backend.dto.fighter;

import java.util.Map;
import java.util.UUID;

import com.fightscene.backend.domain.fighter.Gender;
import com.fightscene.backend.domain.fighter.WeightClass;

public record UpdateFighterDto(
        String firstName,
        String lastName,
        String nickname,
        Gender gender,
        UUID gymId,
        WeightClass weightClass,
        String bio,
        String profilePicUrl,
        String tapologyProfileUrl,
        Map<String, String> externalLinks
) {}
