CREATE TABLE products (
                          id            BIGSERIAL       PRIMARY KEY,
                          name          VARCHAR(150)    NOT NULL,
                          slug          VARCHAR(200)    NOT NULL,
                          description   TEXT            NOT NULL,
                          price         NUMERIC(10, 2)  NOT NULL,
                          category      VARCHAR(20)     NOT NULL,
                          status        VARCHAR(10)     NOT NULL,
                          collection_id BIGINT,
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

CREATE TABLE product_cares (
    id            BIGSERIAL PRIMARY KEY,
    product_id    BIGSERIAL NOT NULL,
    instruction   VARCHAR(255),
    display_order INT,
    CONSTRAINT fk_care_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE product_colors (
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGSERIAL NOT NULL,
    name       VARCHAR(50),
    hex_code   VARCHAR(10),
    stock      INT,
    CONSTRAINT fk_color_product FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE product_images (
    id               BIGSERIAL PRIMARY KEY,
    product_color_id BIGSERIAL NOT NULL,
    url              VARCHAR(255),
    file_name        VARCHAR(100),
    display_order    INT,
    is_cover         BOOLEAN,
    CONSTRAINT fk_image_color FOREIGN KEY (product_color_id) REFERENCES product_colors(id)
);

CREATE TABLE product_variants (
    id               BIGSERIAL PRIMARY KEY,
    product_color_id BIGSERIAL NOT NULL,
    size     VARCHAR(2),
    stock            INT,
    version          INT,
    CONSTRAINT fk_variant_color FOREIGN KEY (product_color_id) REFERENCES product_colors(id),
    CONSTRAINT uk_variant_productSize UNIQUE (product_color_id, size)
);

CREATE TABLE product_compositions (
                                      id         BIGSERIAL    PRIMARY KEY,
                                      type       VARCHAR(20)  NOT NULL,
                                      product_id BIGINT       NOT NULL,
                                      FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE composition_materials (
                                       id             BIGSERIAL   PRIMARY KEY,
                                       name           VARCHAR(50) NOT NULL,
                                       percentage     INT         NOT NULL,
                                       composition_id BIGINT      NOT NULL,
                                       FOREIGN KEY (composition_id) REFERENCES product_compositions(id)
);
