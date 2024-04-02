DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS client;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE client
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR(255)                           NOT NULL,
    surname          VARCHAR(255)                           NOT NULL,
    email            VARCHAR(255)                           NOT NULL,
    phone            VARCHAR(20)                            NOT NULL,
    address          VARCHAR(255)                           NOT NULL,
    city             VARCHAR(255)                           NOT NULL,
    zip_code         VARCHAR(10)                            NOT NULL,
    country          VARCHAR(255)                           NOT NULL
);

CREATE UNIQUE INDEX client_unique_email_idx ON client (email);

CREATE TABLE cars
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    brand            VARCHAR(255)                           NOT NULL,
    model            VARCHAR(255)                           NOT NULL,
    car_year         INTEGER                                NOT NULL,
    color            VARCHAR(255)                           NOT NULL,
    kilometers       INTEGER,
    client_id        INTEGER REFERENCES client(id) ON DELETE SET NULL,
    rented_at        TIMESTAMP
);

CREATE INDEX cars_client_id_idx ON cars (client_id);

CREATE TABLE rental_history
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    car_id           INTEGER REFERENCES cars(id) ON DELETE CASCADE,
    client_id        INTEGER REFERENCES client(id) ON DELETE CASCADE,
    start_date       TIMESTAMP                              NOT NULL,
    end_date         TIMESTAMP                              NOT NULL
);

CREATE INDEX rental_history_car_id_idx ON rental_history (car_id);