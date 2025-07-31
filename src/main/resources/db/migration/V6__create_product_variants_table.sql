CREATE TABLE product_variants (
    id                UUID         NOT NULL DEFAULT gen_random_uuid(),
    sku               VARCHAR(20)  NOT NULL,
    size              VARCHAR(2)   NOT NULL,
    color             VARCHAR(40) NOT NULL,
    quantity_in_stock INT          NOT NULL,
    product_id        UUID         NOT NULL,
    created_at        TIMESTAMP    NOT NULL,
    updated_at        TIMESTAMP    NOT NULL,
    version           BIGINT       NOT NULL,

    CONSTRAINT pk_product_variants PRIMARY KEY (id),
    CONSTRAINT uq_product_variants_sku UNIQUE (sku),
    CONSTRAINT fk_product_variants_on_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE INDEX idx_product_variants_product_id ON product_variants(product_id);