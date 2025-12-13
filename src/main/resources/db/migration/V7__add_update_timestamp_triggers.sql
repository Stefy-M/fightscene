-- Create reusable timestamp update function
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger for video_processing_jobs
CREATE TRIGGER trg_update_video_processing_jobs_timestamp
BEFORE UPDATE ON video_processing_jobs
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();