CREATE TABLE verification_tokens (
    id             BIGSERIAL       PRIMARY KEY,
    token          VARCHAR(255)    NOT NULL UNIQUE,
    used           BOOLEAN         NOT NULL DEFAULT FALSE,
    expires_at     TIMESTAMPTZ     NOT NULL,
    user_id        UUID            NOT NULL,

    CONSTRAINT fk_token_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE INDEX idx_verification_tokens_user_id ON verification_tokens (user_id);
CREATE INDEX idx_verification_tokens_expires_at ON verification_tokens (expires_at) WHERE used = false;