package com.fightscene.backend.domain.video.repository;

import com.fightscene.backend.domain.video.JobStatus;
import com.fightscene.backend.domain.video.VideoProcessingJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoProcessingJobRepository
        extends JpaRepository<VideoProcessingJob, UUID> {

    List<VideoProcessingJob> findByVideo_VideoId(UUID videoId);

    List<VideoProcessingJob> findByStatus(JobStatus status);

    Optional<VideoProcessingJob> findTopByStatusOrderByCreatedAtAsc(
            JobStatus status
    );
}