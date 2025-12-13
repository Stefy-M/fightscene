CREATE TABLE video_processing_jobs (
    job_id          UUID PRIMARY KEY,
    video_id        UUID NOT NULL,
    status          job_status NOT NULL,
    stage           VARCHAR(50),
    retry_count     INT NOT NULL DEFAULT 0,
    error_message   TEXT,
    started_at      TIMESTAMP,
    completed_at    TIMESTAMP,
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP NOT NULL DEFAULT NOW(),

    FOREIGN KEY (video_id) REFERENCES videos(video_id) ON DELETE CASCADE
);

CREATE INDEX idx_jobs_video ON video_processing_jobs(video_id);
CREATE INDEX idx_jobs_status ON video_processing_jobs(status);