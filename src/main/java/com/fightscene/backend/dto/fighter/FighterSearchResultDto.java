package com.fightscene.backend.dto.fighter;

import java.util.UUID;

import com.fightscene.backend.domain.fighter.WeightClass;

public record FighterSearchResultDto(
        UUID fighterId,
        String firstName,
        String lastName,
        String nickname,
        String gymName,
        WeightClass weightClass,
        String profilePicUrl,
        Integer publicVideoCount
) {}
