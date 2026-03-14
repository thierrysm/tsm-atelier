CREATE TABLE collection_images (
    id           UUID PRIMARY KEY,
    url          VARCHAR(255),
    file_name    VARCHAR(100)
);

CREATE TABLE collections (
    id          UUID         PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT         NOT NULL,
    year        INT          NOT NULL,
    image_id    UUID,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    CONSTRAINT fk_collection_image FOREIGN KEY (image_id) REFERENCES collection_images(id)
);