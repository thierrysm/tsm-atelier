CREATE TABLE addresses (
    id           UUID         NOT NULL DEFAULT gen_random_uuid(),
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP    NOT NULL,
    street       VARCHAR(200) NOT NULL,
    number       VARCHAR(20)  NOT NULL,
    complement   VARCHAR(100),
    neighborhood VARCHAR(100) NOT NULL,
    city         VARCHAR(100) NOT NULL,
    state        VARCHAR(2)   NOT NULL,
    zip_code     VARCHAR(8)   NOT NULL,
    is_primary   BOOLEAN      NOT NULL,
    user_id      UUID         NOT NULL,

    CONSTRAINT pk_addresses PRIMARY KEY (id),
    CONSTRAINT fk_addresses_on_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_addresses_user_id ON addresses(user_id);