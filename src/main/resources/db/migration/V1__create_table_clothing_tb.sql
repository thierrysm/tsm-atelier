CREATE TABLE clothing_tb
(
    id          UUID          NOT NULL DEFAULT gen_random_uuid(),
    name        VARCHAR(100)  NOT NULL,
    price       DECIMAL(8, 2) NOT NULL,
    description VARCHAR(200)  NOT NULL,
    type        VARCHAR(11)   NOT NULL,

    PRIMARY KEY (id)
);