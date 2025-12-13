package com.fightscene.backend.domain.video;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "video_processing_jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoProcessingJob {

	@Id
	@Column(name = "job_id", nullable = false, updatable = false)
	private UUID jobId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "video_id", nullable = false)
	private Video video;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private JobStatus status;

	@Column(length = 50)
	private String stage;

	@Column(name = "retry_count", nullable = false)
	private Integer retryCount;

	@Column(columnDefinition = "TEXT")
	private String errorMessage;

	@Column(name = "started_at")
	private Instant startedAt;

	@Column(name = "completed_at")
	private Instant completedAt;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	@PrePersist
	public void prePersist() {
		if (jobId == null)
			jobId = UUID.randomUUID();
		Instant now = Instant.now();
		createdAt = now;
		updatedAt = now;
		if (retryCount == null)
			retryCount = 0;
		if (status == null)
			status = JobStatus.QUEUED;
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}
}