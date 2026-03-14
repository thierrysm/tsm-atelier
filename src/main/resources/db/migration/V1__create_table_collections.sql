CREATE TABLE collections (
    id              BIGSERIAL    PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    slug            VARCHAR(120) NOT NULL,
    description     TEXT         NOT NULL,
    image_url       TEXT         NOT NULL,
    status          VARCHAR(15)  NOT NULL,
    featured        BOOLEAN      NOT NULL,
    show_in_header  BOOLEAN      NOT NULL,
    is_new          BOOLEAN      NOT NULL,
    display_order   INTEGER      NOT NULL,
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP,

    CONSTRAINT uk_collections_name UNIQUE (name),
    CONSTRAINT uk_collections_slug UNIQUE (slug)
);