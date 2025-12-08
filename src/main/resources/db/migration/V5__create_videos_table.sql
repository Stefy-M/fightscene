CREATE TABLE videos (
    video_id            UUID PRIMARY KEY,
    fighter_id          UUID NOT NULL,
    original_file_name  VARCHAR(255),
    s3_original_url     TEXT NOT NULL,
    s3_transcoded_url   TEXT,
    thumbnail_url       TEXT,
    title               VARCHAR(255),
    description         TEXT,
    duration_seconds    INT,
    resolution          VARCHAR(50),
    size_bytes          BIGINT,
    privacy             video_privacy NOT NULL DEFAULT 'PRIVATE',
    status              video_status NOT NULL DEFAULT 'UPLOADED_PENDING_PROCESSING',
    created_at          TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMP NOT NULL DEFAULT NOW(),

    FOREIGN KEY (fighter_id) REFERENCES fighters(fighter_id) ON DELETE CASCADE
);

CREATE INDEX idx_videos_fighter ON videos(fighter_id);
CREATE INDEX idx_videos_privacy ON videos(privacy);
CREATE INDEX idx_videos_public ON videos(fighter_id) WHERE privacy = 'PUBLIC';
