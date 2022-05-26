--changeset author:marakulin-ak:add-vaccine
INSERT INTO t_vaccine (id, name)
VALUES
    (1, 'pfizer'),
    (2, 'sputnik-v');
--rollback TRUNCATE t_vaccine;
