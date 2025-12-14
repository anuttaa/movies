
--changeset Telpuk Nikifor:1
ALTER TABLE awards
    ALTER COLUMN award_description TYPE TEXT,
    ALTER COLUMN award_name TYPE VARCHAR(255);