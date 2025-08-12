CREATE TABLE products (
    id                UUID           NOT NULL DEFAULT gen_random_uuid(),
    name              VARCHAR(255)   NOT NULL,
    description       TEXT,
    price             DECIMAL(10, 2) NOT NULL,
    sku               VARCHAR(30)    NOT NULL,
    category          VARCHAR(15)    NOT NULL,
    is_active         BOOLEAN        NOT NULL,
    promotional_price DECIMAL(10, 2),
    created_at        TIMESTAMP      NOT NULL,
    updated_at        TIMESTAMP      NOT NULL,
    version           BIGINT         NOT NULL,

    CONSTRAINT pk_products PRIMARY KEY (id),
    CONSTRAINT uq_products_name UNIQUE (name),
    CONSTRAINT uq_products_sku UNIQUE (sku)
);

CREATE TABLE product_materials (
    product_id UUID           NOT NULL,
    material   VARCHAR(255)   NOT NULL,

    CONSTRAINT pk_product_materials PRIMARY KEY (product_id, material),
    CONSTRAINT fk_product_materials_on_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE product_care_instructions (
    product_id  UUID           NOT NULL,
    instruction VARCHAR(255)   NOT NULL,

    CONSTRAINT pk_product_care_instructions PRIMARY KEY (product_id, instruction),
    CONSTRAINT fk_product_care_instructions_on_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);