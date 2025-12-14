package com.fightscene.backend.domain.video.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fightscene.backend.domain.video.Video;
import com.fightscene.backend.domain.video.VideoPrivacy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrivacyService {

    public boolean canView(UUID userId, Video video) {
        return video.getPrivacy() == VideoPrivacy.PUBLIC ||
               video.getFighter().getUser().getUserId().equals(userId);
    }

    public void ensureViewable(UUID userId, Video video) {
        if (!canView(userId, video)) {
            throw new SecurityException("Access denied");
        }
    }
}