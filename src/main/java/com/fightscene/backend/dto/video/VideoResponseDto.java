package com.fightscene.backend.dto.video;

import java.util.UUID;

import com.fightscene.backend.domain.video.VideoPrivacy;
import com.fightscene.backend.domain.video.VideoStatus;

public record VideoResponseDto(
        UUID videoId,
        String title,
        String description,
        String thumbnailUrl,
        VideoPrivacy privacy,
        VideoStatus status,
        String playbackUrl,
        Integer durationSeconds,
        String resolution,
        Long sizeBytes
) {}
