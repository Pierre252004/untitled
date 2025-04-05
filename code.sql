-- 0. Create schema
CREATE SCHEMA IF NOT EXISTS project;
SET search_path TO project;

-- 1. Persons
CREATE TABLE project.Persons (
    person_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    middle_initial CHAR(1),
    last_name VARCHAR(50),
    street_name VARCHAR(100),
    street_number VARCHAR(10),
    apartment_number VARCHAR(10),
    city VARCHAR(50),
    country VARCHAR(50),
    zip_code VARCHAR(20)
);

-- 2. Login Table
CREATE TABLE IF NOT EXISTS project.users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100),
    person_id INT REFERENCES project.Persons(person_id)
);

-- 3. Hotel Chains
CREATE TABLE project.HotelChains (
    chain_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(200),
    number_of_hotels INT,
    email VARCHAR(100),
    phone VARCHAR(20)
);

-- 4. Hotels
CREATE TABLE project.Hotels (
    hotel_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(200),
    email VARCHAR(100),
    phone VARCHAR(20),
    rating DECIMAL(2,1),
    number_of_rooms INT,
    chain_id INT REFERENCES project.HotelChains(chain_id)
);

-- 5. Rooms
CREATE TABLE project.Rooms (
    room_id SERIAL PRIMARY KEY,
    hotel_id INT REFERENCES project.Hotels(hotel_id),
    room_number VARCHAR(10),
    price DECIMAL(10,2),
    amenities TEXT[],
    zip_code VARCHAR(20),
    capacity INT,
    view_type VARCHAR(50),
    extendable BOOLEAN,
    state VARCHAR(50)
);


-- 6. Employees
CREATE TABLE project.Employees (
    employee_id SERIAL PRIMARY KEY,
    person_id INT REFERENCES project.Persons(person_id),
    ssn VARCHAR(20) UNIQUE,
    salary DECIMAL(10,2),
    role VARCHAR(50),
    hotel_id INT REFERENCES project.Hotels(hotel_id)
);

-- 7. Clients
CREATE TABLE project.Clients (
    client_id SERIAL PRIMARY KEY,
    person_id INT REFERENCES project.Persons(person_id),
    registration_date DATE
);

-- 8. Report History
CREATE TABLE project.Reports (
    report_id SERIAL PRIMARY KEY,
    room_id INT REFERENCES project.Rooms(room_id),
    history_id VARCHAR(50),
    status VARCHAR(50),
    problem_description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 9. Booking History
CREATE TABLE project.Bookings (
    booking_id SERIAL PRIMARY KEY,
    client_id INT REFERENCES project.Clients(client_id),
    hotel_id INT REFERENCES project.Hotels(hotel_id),
    room_id INT REFERENCES project.Rooms(room_id),
    check_in DATE,
    check_out DATE,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 10. Login Logs
CREATE TABLE IF NOT EXISTS project.LoginLogs (
    log_id SERIAL PRIMARY KEY,
    person_id INT REFERENCES project.Persons(person_id),
    login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- SQL Insert Statements for Sample Data

-- Insert Hotel Chains
INSERT INTO project.HotelChains (name, address, number_of_hotels, email, phone)
VALUES
('Sunrise Hotels', '123 Sunset Blvd, LA, CA', 3, 'info@sunrise.com', '555-1234'),
('Moonlight Resorts', '456 Moon Rd, Miami, FL', 5, 'contact@moonlight.com', '555-5678');

-- Insert Hotels
INSERT INTO project.Hotels (name, address, email, phone, rating, number_of_rooms, chain_id)
VALUES
('Sunrise Downtown', '321 Main St, LA, CA', 'sunrise.dt@sunrise.com', '555-4321', 4.5, 100, 1),
('Moonlight Beach', '789 Shoreline Dr, Miami, FL', 'beach@moonlight.com', '555-8765', 4.0, 150, 2);

-- Insert Rooms
INSERT INTO project.Rooms (hotel_id, room_number, price, amenities, zip_code, capacity, view_type, extendable, state)
VALUES
(1, '101A', 120.00, ARRAY['WiFi', 'TV'], '90001', 2, 'Ocean', true, 'Available'),
(2, '201B', 150.00, ARRAY['WiFi', 'AC'], '33101', 4, 'City', false, 'Available');

-- Insert Persons
INSERT INTO project.Persons (first_name, middle_initial, last_name, street_name, street_number, apartment_number, city, country, zip_code)
VALUES
('John', 'A', 'Doe', 'Oak St', '111', '1A', 'Los Angeles', 'USA', '90001'),
('Jane', 'B', 'Smith', 'Palm Ave', '222', '2B', 'Miami', 'USA', '33101');

-- Insert Employees
INSERT INTO project.Employees (person_id, ssn, salary, role, hotel_id)
VALUES
(1, '123-45-6789', 50000, 'Manager', 1);

-- Insert Clients
INSERT INTO project.Clients (person_id)
VALUES
(2);

-- Insert Reports
INSERT INTO project.Reports (room_id, history_id, status, problem_description)
VALUES
(1, 'RPT101', 'Open', 'Broken AC unit');

-- Insert Bookings
INSERT INTO project.Bookings (client_id, hotel_id, room_id, check_in, check_out)
VALUES
(1, 1, 1, '2025-04-10', '2025-04-15');

CREATE OR REPLACE FUNCTION project.auto_create_user_login() RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO project.users (email, password, name, person_id)
  VALUES (
    CONCAT(NEW.first_name, '.', NEW.last_name, '@hotelapp.com'), -- auto-generated email
    'default123',                                                -- placeholder password (you can later update this)
    CONCAT(NEW.first_name, ' ', NEW.last_name),
    NEW.person_id
  );
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_create_login_on_person
AFTER INSERT ON project.Persons
FOR EACH ROW
EXECUTE FUNCTION project.auto_create_user_login();

CREATE OR REPLACE FUNCTION project.auto_create_user_login() RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO project.users (email, password, name, person_id)
  VALUES (
    LOWER(CONCAT(NEW.first_name, '.', NEW.last_name, '@hotelapp.com')),
    'default123',
    CONCAT(NEW.first_name, ' ', NEW.last_name),
    NEW.person_id
  );
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_create_login_on_person
AFTER INSERT ON project.Persons
FOR EACH ROW
EXECUTE FUNCTION project.auto_create_user_login();

CREATE OR REPLACE FUNCTION project.set_registration_date() RETURNS TRIGGER AS $$
BEGIN
  NEW.registration_date := CURRENT_DATE;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_set_client_registration
BEFORE INSERT ON project.Clients
FOR EACH ROW
EXECUTE FUNCTION project.set_registration_date();

CREATE OR REPLACE FUNCTION project.update_room_status_on_booking() RETURNS TRIGGER AS $$
BEGIN
  UPDATE project.Rooms SET state = 'Occupied' WHERE room_id = NEW.room_id;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_room_status_after_booking
AFTER INSERT ON project.Bookings
FOR EACH ROW
EXECUTE FUNCTION project.update_room_status_on_booking();

CREATE OR REPLACE FUNCTION project.log_login_time() RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO project.LoginLogs (person_id) VALUES (NEW.person_id);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_log_login
AFTER UPDATE ON project.Persons
FOR EACH ROW
WHEN (OLD.first_name IS DISTINCT FROM NEW.first_name)
EXECUTE FUNCTION project.log_login_time();

CREATE OR REPLACE FUNCTION project.update_hotel_count() RETURNS TRIGGER AS $$
BEGIN
  UPDATE project.HotelChains
  SET number_of_hotels = (SELECT COUNT(*) FROM project.Hotels WHERE chain_id = NEW.chain_id)
  WHERE chain_id = NEW.chain_id;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_hotel_count
AFTER INSERT ON project.Hotels
FOR EACH ROW
EXECUTE FUNCTION project.update_hotel_count();
