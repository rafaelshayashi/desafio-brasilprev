INSERT INTO address (id, country, state, city, street, postal_code, created_at, updated_at)
VALUES (1, 'EUA', 'CA', 'Los Angeles', '10880 Malibu Point', '90265', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO address (id, country, state, city, street, postal_code, created_at, updated_at)
VALUES (2, 'EUA', 'CA', 'Los Angeles', '20000 Malibu Point', '90266', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO customer (uuid, cpf, name, address_id, created_at, updated_at)
VALUES ('4647765e-6491-47aa-82bd-c08f87439421', '004.005.006-10', 'Tony Stark', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO customer (uuid, cpf, name, address_id, created_at, updated_at)
VALUES ('72c5d8c5-539a-42f0-8a01-5c4ec7cdb924', '005.001.009-10', 'Steve Rogers', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());