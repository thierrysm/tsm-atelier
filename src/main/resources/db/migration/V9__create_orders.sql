-- =====================================================================
-- V12 — Pedidos (orders)
-- =====================================================================
-- Snapshot em order_items: o pedido histórico permanece imutável ainda
-- que produto/preço mudem depois.

CREATE TABLE orders (
    id                          UUID            PRIMARY KEY,
    user_id                     UUID            NOT NULL,

    status                      VARCHAR(30)     NOT NULL,

    total_amount                NUMERIC(12, 2)  NOT NULL,

    ship_recipient_name         VARCHAR(120)    NOT NULL,
    ship_cep                    VARCHAR(9)      NOT NULL,
    ship_street                 VARCHAR(255)    NOT NULL,
    ship_number                 VARCHAR(20)     NOT NULL,
    ship_complement             VARCHAR(100),
    ship_neighborhood           VARCHAR(100)    NOT NULL,
    ship_city                   VARCHAR(100)    NOT NULL,
    ship_state                  VARCHAR(2)      NOT NULL,

    created_at                  TIMESTAMPTZ     NOT NULL,
    updated_at                  TIMESTAMPTZ,
    version                     INTEGER         NOT NULL DEFAULT 0,

    CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX idx_orders_user_id ON orders (user_id);
CREATE INDEX idx_orders_status  ON orders (status);

CREATE TABLE order_items (
    id                  BIGSERIAL       PRIMARY KEY,
    order_id            UUID            NOT NULL,
    product_variant_id  BIGINT          NOT NULL,

    -- snapshot do produto no momento do pedido (não FK por design):
    product_name        VARCHAR(150)    NOT NULL,
    color_name          VARCHAR(50)     NOT NULL,
    size_name           VARCHAR(20)     NOT NULL,
    image_url           TEXT,

    unit_price          NUMERIC(12, 2)  NOT NULL,
    quantity            INTEGER         NOT NULL CHECK (quantity > 0),

    CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

CREATE INDEX idx_order_items_order_id ON order_items (order_id);
