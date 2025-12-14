--changeset Volkov Pavel:1
ALTER TABLE users
    ALTER COLUMN is_email_verified SET DEFAULT FALSE,
    ALTER COLUMN is_email_verified SET NOT NULL;