package com.fightscene.backend.dto.video;

import com.fightscene.backend.domain.video.VideoPrivacy;

public record UpdateVideoDto(
        String title,
        String description,
        VideoPrivacy privacy
) {}
