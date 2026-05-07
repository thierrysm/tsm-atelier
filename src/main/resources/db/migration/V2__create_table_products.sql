CREATE TABLE products (
                          id            BIGSERIAL       PRIMARY KEY,
                          name          VARCHAR(150)    NOT NULL,
                          slug          VARCHAR(200)    NOT NULL,
                          description   TEXT            NOT NULL,
                          price         NUMERIC(10, 2)  NOT NULL,
                          category      VARCHAR(20)     NOT NULL,
                          status        VARCHAR(10)     NOT NULL,
                          collection_id BIGINT,
                          version       INTEGER         NOT NULL DEFAULT 0,
                          disabled_at   TIMESTAMPTZ,
                          created_at    TIMESTAMPTZ,
                          updated_at    TIMESTAMPTZ,

                          CONSTRAINT fk_products_collection
                              FOREIGN KEY (collection_id)
                                  REFERENCES collections(id)
                                  ON DELETE SET NULL
);

CREATE UNIQUE INDEX idx_product_unique_active_name
    ON products (name)
    WHERE status = 'ACTIVE';

CREATE INDEX idx_products_collection_id ON products (collection_id);

CREATE TABLE product_colors (
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGINT    NOT NULL,
    name       VARCHAR(50),
    hex_code   VARCHAR(10),
    stock      INT,
    version    INTEGER   NOT NULL DEFAULT 0,
    CONSTRAINT fk_color_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE INDEX idx_product_colors_product_id ON product_colors (product_id);
CREATE UNIQUE INDEX uk_product_colors_product_id_lower_name ON product_colors (product_id, LOWER(name));
CREATE UNIQUE INDEX uk_product_colors_product_id_lower_hex_code ON product_colors (product_id, LOWER(hex_code)) WHERE hex_code IS NOT NULL;

CREATE TABLE product_images (
    id               BIGSERIAL PRIMARY KEY,
    product_color_id BIGINT    NOT NULL,
    url              VARCHAR(255),
    file_name        VARCHAR(100),
    display_order    INT       NOT NULL,
    is_cover         BOOLEAN   NOT NULL,
    CONSTRAINT fk_image_color FOREIGN KEY (product_color_id) REFERENCES product_colors(id)
);

CREATE INDEX idx_product_images_product_color_id ON product_images (product_color_id);

CREATE TABLE product_variants (
    id               BIGSERIAL PRIMARY KEY,
    product_color_id BIGINT    NOT NULL,
    size             VARCHAR(2),
    stock            INT,
    version          INT,
    CONSTRAINT fk_variant_color FOREIGN KEY (product_color_id) REFERENCES product_colors(id),
    CONSTRAINT uk_variant_productSize UNIQUE (product_color_id, size)
);

CREATE INDEX idx_product_variants_product_color_id ON product_variants (product_color_id);

CREATE TABLE product_compositions (
    id         BIGSERIAL    PRIMARY KEY,
    type       VARCHAR(20)  NOT NULL,
    product_id BIGINT       NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE INDEX idx_product_compositions_product_id ON product_compositions (product_id);

CREATE TABLE composition_materials (
    composition_id BIGINT       NOT NULL,
    name           VARCHAR(50)  NOT NULL,
    percentage     INT          NOT NULL,
    CONSTRAINT fk_materials_composition FOREIGN KEY (composition_id) REFERENCES product_compositions(id) ON DELETE CASCADE
);

CREATE INDEX idx_composition_materials_composition_id ON composition_materials (composition_id);

CREATE TABLE product_cares (
    product_id       BIGINT       NOT NULL,
    care_instruction VARCHAR(255) NOT NULL,
    CONSTRAINT fk_product_cares_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE INDEX idx_product_cares_product_id ON product_cares (product_id);
