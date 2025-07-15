CREATE TABLE refresh_tokens (
    id           UUID         NOT NULL DEFAULT gen_random_uuid(),
    user_id      UUID         NOT NULL,
    token        VARCHAR(255) NOT NULL,
    revoked      BOOLEAN      NOT NULL DEFAULT false,
    expiry_date  TIMESTAMP    NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL,

    CONSTRAINT pk_refresh_tokens PRIMARY KEY (id),
    CONSTRAINT uq_refresh_tokens_token UNIQUE (token),
    CONSTRAINT fk_refresh_tokens_on_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);