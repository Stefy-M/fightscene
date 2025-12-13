package com.fightscene.backend.domain.video;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fightscene.backend.domain.fighter.Fighter;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {

	@Id
	@Column(name = "video_id", nullable = false, updatable = false)
	private UUID videoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fighter_id", nullable = false)
	private Fighter fighter;

	@OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
	private List<VideoProcessingJob> videoProcessingJobs = new ArrayList<>();

	@Column(name = "original_file_name", length = 255)
	private String originalFileName;

	@Column(name = "s3_original_url", length = 255, nullable = false)
	private String s3OriginalUrl;

	@Column(name = "s3_transcoded_url", length = 500)
	private String s3TranscodedUrl;

	@Column(name = "thumbnail_url", length = 500)
	private String thumbnailUrl;

	@Column(length = 255)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(name = "duration_seconds")
	private Integer durationSeconds;

	@Column(length = 50)
	private String resolution;

	@Column(name = "size_bytes")
	private long sizeBytes;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private VideoPrivacy privacy;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private VideoStatus status;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

	// Returned dynamically to frontend; not in DB
	@Transient
	private String playbackUrl;

	@PrePersist
	public void prePersist() {
		if (videoId == null)
			videoId = UUID.randomUUID();
		Instant now = Instant.now();
		createdAt = now;
		updatedAt = now;
		if (privacy == null)
			privacy = VideoPrivacy.PRIVATE;
		if (status == null)
			status = VideoStatus.UPLOAD_PENDING_PROCESSING;
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}

}
