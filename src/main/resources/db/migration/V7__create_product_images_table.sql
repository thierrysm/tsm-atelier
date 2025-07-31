CREATE TABLE product_images (
    id          UUID         NOT NULL DEFAULT gen_random_uuid(),
    image_url   VARCHAR(255) NOT NULL,
    alt_text    VARCHAR(255),
    is_primary  BOOLEAN      NOT NULL,
    product_id  UUID         NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    updated_at  TIMESTAMP    NOT NULL,
    version     BIGINT       NOT NULL,

    CONSTRAINT pk_product_images PRIMARY KEY (id),
    CONSTRAINT fk_product_images_on_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE INDEX idx_product_images_product_id ON product_images(product_id);