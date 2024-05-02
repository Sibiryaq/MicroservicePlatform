
CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(50) NOT NULL UNIQUE,
                          description VARCHAR NOT NULL,
                          price NUMERIC(10, 2),
                          stock INTEGER
);

INSERT INTO products (name, description, price, stock)
VALUES ('Apple', 'Fruit', 9.99, 100),
       ('Potato', 'Fruit', 1.20, 300),
       ('Axe', 'Instrument', 5.3, 200),
       ('Hammer', 'Instrument', 5.3, 200),
       ('Corn', 'Vegetable', 5.3, 200);
