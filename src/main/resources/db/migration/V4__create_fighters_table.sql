CREATE TABLE fighters (
    fighter_id           UUID PRIMARY KEY,
    user_id              UUID NOT NULL UNIQUE,
    gym_id               UUID,
    first_name           VARCHAR(255) NOT NULL,
    last_name            VARCHAR(255) NOT NULL,
    nickname             VARCHAR(255),
    gender               gender_enum NOT NULL,
    weight_class         weight_class_enum NOT NULL,
    bio                  TEXT,
    profile_pic_url      TEXT,
    tapology_profile_url TEXT,
    external_links       JSONB DEFAULT '{}'::jsonb,
    storage_used_bytes   BIGINT DEFAULT 0,
    created_at           TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at           TIMESTAMP NOT NULL DEFAULT NOW(),

    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (gym_id)  REFERENCES gyms(gym_id)
);

CREATE INDEX idx_fighters_last_name ON fighters(last_name);
CREATE INDEX idx_fighters_weight_class ON fighters(weight_class);
CREATE INDEX idx_fighters_gender ON fighters(gender);
CREATE INDEX idx_fighters_name_search ON fighters(last_name, first_name);