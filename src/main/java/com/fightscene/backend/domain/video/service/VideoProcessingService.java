package com.fightscene.backend.domain.video.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fightscene.backend.domain.video.JobStatus;
import com.fightscene.backend.domain.video.Video;
import com.fightscene.backend.domain.video.VideoProcessingJob;
import com.fightscene.backend.domain.video.repository.VideoProcessingJobRepository;
import com.fightscene.backend.domain.video.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoProcessingService {

	private final VideoProcessingJobRepository jobRepository;
	private final VideoRepository videoRepository;

	public VideoProcessingJob createProcessingJob(UUID videoId) {

		Video video = videoRepository.findById(videoId).orElseThrow(() -> new IllegalStateException("Video not found"));

		VideoProcessingJob job = VideoProcessingJob.builder().video(video).status(JobStatus.PROCESSING).build();

		return jobRepository.save(job);
	}

	public List<VideoProcessingJob> getJobsByVideo(UUID videoId) {
		return jobRepository.findByVideo_VideoId(videoId);
	}
	
	
}
