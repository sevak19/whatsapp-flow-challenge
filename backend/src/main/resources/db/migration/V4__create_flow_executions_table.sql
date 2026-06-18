CREATE TABLE flow_executions (
    id BIGSERIAL PRIMARY KEY,

    flow_id BIGINT NOT NULL,

    contact_id BIGINT NOT NULL,

    current_step INTEGER NOT NULL,

    next_execution_at TIMESTAMP NOT NULL,

    status VARCHAR(30) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_execution_flow
        FOREIGN KEY (flow_id)
        REFERENCES flows(id),

    CONSTRAINT fk_execution_contact
        FOREIGN KEY (contact_id)
        REFERENCES contacts(id)
);