package com.fightscene.backend.domain.video.repository;

import com.fightscene.backend.domain.video.Video;
import com.fightscene.backend.domain.video.VideoPrivacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID> {

	List<Video> findByFighter_FighterId(UUID fighterId);

	List<Video> findByFighter_FighterIdAndPrivacy(UUID fighterId, VideoPrivacy privacy);

	Optional<Video> findByVideoId(UUID videoId);
}