package com.fightscene.backend.dto.video;

public record VideoMetadataDto(
        Integer durationSeconds,
        String resolution,
        String thumbnailUrl,
        String transcodedUrl
) {}
