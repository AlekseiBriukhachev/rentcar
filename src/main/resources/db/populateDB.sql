INSERT INTO client (name, surname, email, phone, address, city, zip_code, country, rent_history)
VALUES ('John', 'Doe', 'john@email.com', '123456789', 'Main Street 1', 'New York', '10001', 'USA', 'Rent history 1'),
       ('Jane', 'Doe', 'jane@email.com', '987654321', 'Main Street 2', 'New York', '10001', 'USA', 'Rent history 2'),
       ('Tom', 'Smith', 'tom@email.com', '123456789', 'Main Street 3', 'New York', '10001', 'USA', 'Rent history 3');

INSERT INTO cars (brand, model, car_year, color, kilometers, client_id, rented_at)
VALUES ('BMW', 'X5', 2015, 'Black', 10000, null, null),
       ('Audi', 'A6', 2016, 'White', 20000, null, null),
       ('Mercedes', 'S500', 2017, 'Silver', 30000, null, null),
       ('Toyota', 'Camry', 2018, 'Blue', 40000, null, null),
       ('Volkswagen', 'Passat', 2019, 'Red', 50000, null, null);


