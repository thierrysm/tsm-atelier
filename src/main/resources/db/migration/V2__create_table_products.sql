CREATE TABLE products (
    id            UUID PRIMARY KEY,
    name          VARCHAR(100)   NOT NULL,
    alias         VARCHAR(150)   NOT NULL,
    description   TEXT           NOT NULL,
    price         DECIMAL(10, 2) NOT NULL,
    material      VARCHAR(100)   NOT NULL,
    collection_id UUID,
    active        BOOLEAN        NOT NULL,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    disabled_at   TIMESTAMP,

    CONSTRAINT fk_product_collection
    FOREIGN KEY (collection_id)
    REFERENCES collections(id)
    ON DELETE SET NULL
);

CREATE UNIQUE INDEX idx_product_unique_active_name
    ON products (name)
    WHERE active = true;

CREATE TABLE product_cares (
    id            UUID PRIMARY KEY,
    product_id    UUID NOT NULL,
    instruction   VARCHAR(255),
    display_order INT,
    CONSTRAINT fk_care_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE product_colors (
    id         UUID PRIMARY KEY,
    product_id UUID NOT NULL,
    name       VARCHAR(50),
    hex_code   VARCHAR(10),
    stock      INT,
    CONSTRAINT fk_color_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE product_images (
    id               UUID PRIMARY KEY,
    product_color_id UUID NOT NULL,
    url              VARCHAR(255),
    file_name        VARCHAR(100),
    display_order    INT,
    is_cover         BOOLEAN,
    CONSTRAINT fk_image_color FOREIGN KEY (product_color_id) REFERENCES product_colors(id)
);

CREATE TABLE product_variants (
    id               UUID PRIMARY KEY,
    product_color_id UUID NOT NULL,
    product_size     VARCHAR(2),
    stock            INT,
    version          INT,
    CONSTRAINT fk_variant_color FOREIGN KEY (product_color_id) REFERENCES product_colors(id),
    CONSTRAINT uk_variant_productSize UNIQUE (product_color_id, product_size)
);
