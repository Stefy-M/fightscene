package com.fightscene.backend.domain.video;

public enum JobStatus {
	QUEUED,
	PROCESSING,
	TRANSCODING,
	THUMBNAIL_GENERATION,
	UPLOADING,
	COMPLETED,
	FAILED
}
