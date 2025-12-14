package com.fightscene.backend.domain.video.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fightscene.backend.domain.fighter.Fighter;
import com.fightscene.backend.domain.fighter.repository.FighterRepository;
import com.fightscene.backend.domain.video.Video;
import com.fightscene.backend.domain.video.VideoPrivacy;
import com.fightscene.backend.domain.video.VideoStatus;
import com.fightscene.backend.domain.video.repository.VideoRepository;
import com.fightscene.backend.dto.video.FileUploadRequestDto;
import com.fightscene.backend.dto.video.PresignedUploadUrlDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoService {

	private final VideoRepository videoRepository;
	private final FighterRepository fighterRepository;
	private final StorageService storageService;
	private final PrivacyService privacyService;
	private final VideoProcessingService videoProcessingService;
	
	public PresignedUploadUrlDto requestUpload(UUID fighterId, FileUploadRequestDto dto) {
		
		Fighter fighter = fighterRepository.findById(fighterId)
				.orElseThrow(() -> new IllegalStateException("Fighter not found"));
		
		Video video = Video.builder()
				.fighter(fighter)
				.title(dto.title())
				.description(dto.description())
				.originalFileName(dto.originalFileName())
				.sizeBytes(dto.sizeBytes())
				.privacy(VideoPrivacy.PRIVATE)
				.status(VideoStatus.UPLOAD_PENDING_PROCESSING)
				.build();
				
		
		videoRepository.save(video);
		
		return storageService.generateUploadUrl(fighterId, video.getVideoId(), dto);
	}
	
	public void finalizeUpload(UUID videoId) {
		videoProcessingService.createProcessingJob(videoId);
	}
	
	 public void deleteVideo(UUID requesterId, UUID videoId) {
	        Video video = videoRepository.findById(videoId)
	                .orElseThrow(() -> new IllegalStateException("Video not found"));

	        privacyService.ensureViewable(requesterId, video);
	        storageService.deleteAssets(video);
	        videoRepository.delete(video);
	    }
}
