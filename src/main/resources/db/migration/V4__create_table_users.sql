CREATE TABLE users (
    id              UUID           PRIMARY KEY,
    email           VARCHAR(255)   NOT NULL     UNIQUE,
    password        VARCHAR(255)   NOT NULL,
    role            VARCHAR(10)    NOT NULL,
    status          VARCHAR(10)    NOT NULL     DEFAULT 'INACTIVE',
    email_verified  BOOLEAN       NOT NULL     DEFAULT FALSE,
    created_at      TIMESTAMPTZ,
    updated_at      TIMESTAMPTZ
)