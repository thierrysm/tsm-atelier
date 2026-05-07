CREATE TABLE addresses (
                           id                BIGSERIAL    PRIMARY KEY,
                           cep               VARCHAR(9)   NOT NULL,
                           street            VARCHAR(255) NOT NULL,
                           number            VARCHAR(10)  NOT NULL,
                           complement        VARCHAR(100),
                           neighborhood      VARCHAR(100) NOT NULL,
                           city              VARCHAR(100) NOT NULL,
                           state             VARCHAR(2)   NOT NULL,
                           is_default        BOOLEAN      NOT NULL DEFAULT FALSE,
                           client_profile_id BIGINT       NOT NULL,

                           CONSTRAINT fk_address_profile
                               FOREIGN KEY (client_profile_id) REFERENCES client_profiles(id)
);

CREATE INDEX idx_addresses_client_profile_id ON addresses (client_profile_id);