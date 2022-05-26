--changeset author:marakulin-ak:add-vaccination_point
INSERT INTO t_vaccination_point (id, name, code_point, address_id)
VALUES
    (1, 'hospital_3000', '123', 1),
    (2, 'Bolnichka', 'B123', 2),
    (3, 'hospital123', 'H123', 3),
    (4, 'clinic_3000', 'C123', 4),
    (5, 'try_hospital', '1289', 5);
--rollback TRUNCATE t_vaccination_point;
