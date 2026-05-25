CREATE TABLE media_assets (
                 id              BIGSERIAL    PRIMARY KEY,
                 position        VARCHAR(30)  NOT NULL,
                 file_name       VARCHAR(255),
                 url             VARCHAR(255) NOT NULL,
                 alt_text        VARCHAR(255),
                 link_url        VARCHAR(255),
                 title           VARCHAR(100),
                 subtitle        VARCHAR(50),
                 button_text     VARCHAR(50),
                 created_at      TIMESTAMPTZ,
                 updated_at      TIMESTAMPTZ,

                 CONSTRAINT uk_media_asset_positon UNIQUE (position)
);