INSERT INTO address (id, country, state, city, street, postal_code, created_at, updated_at)
VALUES (1, 'EUA', 'CA', 'Los Angeles', '10880 Malibu Point', '90265', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO customer (uuid, cpf, name, address_id, created_at, updated_at)
VALUES ('4647765e-6491-47aa-82bd-c08f87439421', '12345678910', 'Tony Stark', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());