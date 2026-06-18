CREATE TABLE flow_steps (
    id BIGSERIAL PRIMARY KEY,

    flow_id BIGINT NOT NULL,

    step_order INTEGER NOT NULL,

    message TEXT NOT NULL,

    delay_minutes INTEGER NOT NULL,

    CONSTRAINT fk_flow_steps_flow
        FOREIGN KEY (flow_id)
        REFERENCES flows(id)
        ON DELETE CASCADE
);