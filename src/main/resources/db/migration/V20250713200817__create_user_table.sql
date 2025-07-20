CREATE TABLE IF NOT EXISTS "user"
(
    id         BIGSERIAL PRIMARY KEY,
    handle     VARCHAR(32)                         NOT NULL,
    nickname   VARCHAR(32)                         NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
ALTER TABLE "user"
    ADD CONSTRAINT user_handle_unique UNIQUE (handle);
