--liquibase formatted sql

--changeset author:marakulin-ak:create-t_region-table
CREATE TABLE t_region (
            id SERIAL PRIMARY KEY,
            name VARCHAR(64) NOT NULL
);
--rollback DROP TABLE t_region;

--changeset author:marakulin-ak:create-t_city-table
CREATE TABLE t_city (
            id SERIAL PRIMARY KEY,
            name VARCHAR(64) NOT NULL,
            region_id INT REFERENCES t_region(id)
);
--rollback DROP TABLE t_city;

--changeset author:marakulin-ak:create-t_address-table
CREATE TABLE t_address (
            id BIGSERIAL PRIMARY KEY,
            house VARCHAR(16) NOT NULL,
            street VARCHAR(64) NOT NULL,
            city_id INT REFERENCES t_city(id)
);
--rollback DROP TABLE t_address;

--changeset author:marakulin-ak:create-t_vaccine-table
CREATE TABLE t_vaccine (
            id SERIAL PRIMARY KEY,
            name VARCHAR(64) NOT NULL
);
--rollback DROP TABLE t_vaccine;

--changeset author:marakulin-ak:create-t_vaccination_point-table
CREATE TABLE t_vaccination_point (
            id SERIAL PRIMARY KEY,
            name VARCHAR(64) NOT NULL,
            code_point VARCHAR(8) NOT NULL,
            address_id BIGINT REFERENCES t_address(id)
);
--rollback DROP TABLE t_vaccination_point;

--changeset author:marakulin-ak:create-t_vaccination-table
CREATE TABLE t_vaccination (
             id BIGSERIAL PRIMARY KEY,
             person_name VARCHAR(64) NOT NULL,
             person_document_number VARCHAR(32) NOT NULL,
             ampoule_number VARCHAR(8) NOT NULL,
             create_at TIMESTAMP NOT NULL,
             vaccination_date TIMESTAMP NOT NULL,
             vaccination_point_id BIGINT REFERENCES t_vaccination_point(id),
             vaccine_id BIGINT REFERENCES t_vaccine(id)
);
--rollback DROP TABLE t_vaccination;