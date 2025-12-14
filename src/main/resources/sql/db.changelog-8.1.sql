--changeset Telpuk Nikifor:1
ALTER TABLE review
    ALTER COLUMN created_at SET DEFAULT NOW(),
    ALTER COLUMN created_at SET NOT NULL;

--changeset Telpuk Nikifor:2
UPDATE users
SET role = 'GUEST'
WHERE role IS NULL;

--changeset Telpuk Nikifor:2
ALTER TABLE users
    ALTER COLUMN role SET DEFAULT 'GUEST',
    ALTER COLUMN role SET NOT NULL;