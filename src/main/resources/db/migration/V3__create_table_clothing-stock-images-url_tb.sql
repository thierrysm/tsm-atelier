CREATE TABLE clothing_stock_images_url_tb
(
    clothing_stock_id UUID         NOT NULL,
    image_url         VARCHAR(255) NOT NULL,

    PRIMARY KEY (clothing_stock_id, image_url),
    CONSTRAINT fk_clothing_stock
        FOREIGN KEY (clothing_stock_id)
            REFERENCES clothing_stock_tb (id)
            ON DELETE CASCADE
);