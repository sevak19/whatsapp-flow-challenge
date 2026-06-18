CREATE TABLE send_queue (
    id BIGSERIAL PRIMARY KEY,

    contact_id BIGINT NOT NULL,

    campaign_id BIGINT,

    message TEXT NOT NULL,

    scheduled_at TIMESTAMP NOT NULL,

    sent_at TIMESTAMP,

    status VARCHAR(30) NOT NULL,

    error_message TEXT,

    CONSTRAINT fk_queue_contact
        FOREIGN KEY (contact_id)
        REFERENCES contacts(id),

    CONSTRAINT fk_queue_campaign
        FOREIGN KEY (campaign_id)
        REFERENCES campaigns(id)
);