CREATE TABLE video_processing_jobs (
    job_id        UUID PRIMARY KEY,
    video_id      UUID NOT NULL,
    status        video_status NOT NULL,
    error_message TEXT,
    created_at    TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMP NOT NULL DEFAULT NOW(),

    FOREIGN KEY (video_id) REFERENCES videos(video_id) ON DELETE CASCADE
);