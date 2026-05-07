CREATE TABLE client_profiles (
                                 id         BIGSERIAL    PRIMARY KEY,
                                 first_name VARCHAR(60),
                                 last_name  VARCHAR(60),
                                 cpf        VARCHAR(14)  UNIQUE,
                                 phone      VARCHAR(11),
                                 user_id    UUID         NOT NULL UNIQUE,
                                 created_at    TIMESTAMPTZ,
                                 updated_at    TIMESTAMPTZ,

                                 CONSTRAINT fk_profile_user FOREIGN KEY (user_id) REFERENCES users(id)
);