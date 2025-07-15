CREATE TABLE users (
    id                        UUID         NOT NULL DEFAULT gen_random_uuid(),
    full_name                 VARCHAR(255) NOT NULL,
    cpf                       VARCHAR(11)  NOT NULL,
    email                     VARCHAR(255) NOT NULL,
    password                  VARCHAR(255) NOT NULL,
    phone                     VARCHAR(11)  NOT NULL,
    created_at                TIMESTAMP    NOT NULL,
    updated_at                TIMESTAMP    NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_users_cpf UNIQUE (cpf),
    CONSTRAINT uq_users_email UNIQUE (email)
);