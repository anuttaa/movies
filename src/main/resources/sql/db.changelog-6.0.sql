--changeset Telpuk Nikifor:1
ALTER TABLE users
    ADD is_email_verified BOOLEAN;

--changeset Telpuk Nikifor:2
CREATE TABLE confirmation_token
(
    id           SERIAL PRIMARY KEY,
    token        VARCHAR(255)                NOT NULL,
    user_id      BIGINT                      NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    expires_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    confirmed_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT fk_confirmation_token_user FOREIGN KEY (user_id) REFERENCES "users" (user_id)
);