package com.fightscene.backend.dto.video;

import java.util.UUID;

public record PresignedUploadUrlDto(
        UUID videoId,
        String uploadUrl,
        String method,
        Long expiresAt
) {
    public PresignedUploadUrlDto(UUID videoId, String uploadUrl, Long expiresAt) {
        this(videoId, uploadUrl, "PUT", expiresAt);
    }
}
