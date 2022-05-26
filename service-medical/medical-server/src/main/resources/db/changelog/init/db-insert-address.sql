--changeset author:marakulin-ak:add-region
INSERT INTO t_region (id, name)
VALUES
    (1, 'Perm_region'),
    (2, 'region'),
    (3, 'Moscow_region'),
    (4, 'Tatarstan_republic');
--rollback TRUNCATE t_region;

--changeset author:marakulin-ak:add-city
INSERT INTO t_city (id, name, region_id)
VALUES
    (1, 'Perm', 1),
    (2, 'city', 2),
    (3, 'Moscow', 3),
    (4, 'Kazan', 4),
    (5, 'Kazan-2', 4),
    (6, 'Ivanovo', 3);
--rollback TRUNCATE t_city;

--changeset author:marakulin-ak:add-address
INSERT INTO t_address (id, house, street, city_id)
VALUES
    (1, '4', 'kalatyshkina', 1),
    (2, '5a', 'pushkina', 3),
    (3, '1', 'street', 2),
    (4, '56', 'tata', 6),
    (5, '25', 'street', 2);
--rollback TRUNCATE t_address;
