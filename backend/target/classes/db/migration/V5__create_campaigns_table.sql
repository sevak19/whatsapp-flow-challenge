CREATE TABLE campaigns (
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,

    message TEXT NOT NULL,

    min_delay_seconds INTEGER NOT NULL,

    max_delay_seconds INTEGER NOT NULL,

    status VARCHAR(30) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);