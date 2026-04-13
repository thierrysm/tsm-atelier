CREATE TABLE media_assets (
                 id              BIGSERIAL    PRIMARY KEY,
                 position        VARCHAR(30)  NOT NULL,
                 url             TEXT         NOT NULL,
                 file_name       VARCHAR(60),
                 alt_text        VARCHAR(60),
                 link_url        VARCHAR(60),
                 created_at    TIMESTAMPTZ,
                 updated_at    TIMESTAMPTZ,

                 CONSTRAINT uk_media_asset_positon UNIQUE (position)
);