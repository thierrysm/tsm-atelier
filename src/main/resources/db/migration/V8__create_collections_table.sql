CREATE TABLE collections (
    id            UUID           NOT NULL,
    name          VARCHAR(255)   NOT NULL,
    slug          VARCHAR(255)   NOT NULL,
    description   TEXT,
    is_active     BOOLEAN        NOT NULL,
    created_at    TIMESTAMP      NOT NULL,
    updated_at    TIMESTAMP      NOT NULL,
    version       BIGINT         NOT NULL,

    CONSTRAINT pk_collections PRIMARY KEY (id),
    CONSTRAINT uq_collections_name UNIQUE (name),
    CONSTRAINT uq_collections_slug UNIQUE (slug)
);

ALTER TABLE products
ADD COLUMN collection_id UUID;

ALTER TABLE products
ADD CONSTRAINT fk_products_on_collection
FOREIGN KEY (collection_id) REFERENCES collections(id);