CREATE TABLE clothing_stock_tb
(
    id          UUID         NOT NULL DEFAULT gen_random_uuid(),
    clothing_id UUID         NOT NULL,
    color       VARCHAR(100) NOT NULL,
    size        VARCHAR(2)   NOT NULL,
    quantity    INT          NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_clothing
        FOREIGN KEY (clothing_id)
            REFERENCES clothing_tb (id)
            ON DELETE CASCADE
);