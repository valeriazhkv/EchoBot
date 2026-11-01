CREATE TABLE users (
                       id INTEGER PRIMARY KEY,
                       telegram_id BIGINT NOT NULL UNIQUE,
                       username VARCHAR(255),
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);