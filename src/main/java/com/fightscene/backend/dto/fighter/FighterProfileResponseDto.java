package com.fightscene.backend.dto.fighter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fightscene.backend.domain.fighter.Gender;
import com.fightscene.backend.domain.fighter.WeightClass;
import com.fightscene.backend.dto.gym.GymDto;
import com.fightscene.backend.dto.video.VideoResponseDto;

public record FighterProfileResponseDto(
        UUID fighterId,
        String firstName,
        String lastName,
        String nickname,
        Gender gender,
        GymDto gym,
        WeightClass weightClass,
        String bio,
        String profilePicUrl,
        String tapologyProfileUrl,
        Map<String, String> externalLinks,
        List<VideoResponseDto> videos
) {}
