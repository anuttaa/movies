--changeset Telpuk Nikifor:1
ALTER TABLE users
    ADD role VARCHAR(15);

--changeset Telpuk Nikifor:2
CREATE TABLE IF NOT EXISTS tokens
(
    id            SERIAL PRIMARY KEY,
    access_token  VARCHAR(300) NOT NULL,
    refresh_token VARCHAR(300) NOT NULL,
    logged_out    BOOLEAN DEFAULT FALSE,
    user_id       INTEGER,
    CONSTRAINT fk_token_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);