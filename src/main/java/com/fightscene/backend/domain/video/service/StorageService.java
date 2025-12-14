package com.fightscene.backend.domain.video.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fightscene.backend.domain.video.Video;
import com.fightscene.backend.dto.video.FileUploadRequestDto;
import com.fightscene.backend.dto.video.PresignedUploadUrlDto;

@Service
public class StorageService {
	
	  public PresignedUploadUrlDto generateUploadUrl(
	            UUID fighterId,
	            UUID videoId,
	            FileUploadRequestDto dto
	    ) {

		   String objectKey = "fighters/%s/videos/%s/%s"
	                .formatted(fighterId, videoId, dto.originalFileName());

	        String presignedUrl =
	                "https://storage.example.com/" + objectKey;

	        long expiresAt = System.currentTimeMillis() + (15 * 60 * 1000); // 15 min

	        return new PresignedUploadUrlDto(videoId, presignedUrl, expiresAt);
	    }
	  

	    public void deleteAssets(Video video) {
	        // delete object from storage
	    }
}
