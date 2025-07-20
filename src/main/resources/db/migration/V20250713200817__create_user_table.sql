CREATE TABLE IF NOT EXISTS user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    handle     VARCHAR(32)                                                    NOT NULL,
    nickname   VARCHAR(32)                                                    NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT user_handle_unique UNIQUE (handle)
);
