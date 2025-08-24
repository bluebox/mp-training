CREATE DATABASE orga;
USE orga;

-- DROP TABLE IF EXISTS Roles;

CREATE TABLE Roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    role_code VARCHAR(10) UNIQUE,
    role_name VARCHAR(100) UNIQUE NOT NULL
);

ALTER TABLE Roles
ADD COLUMN status VARCHAR(10) DEFAULT 'ACTIVE';


DELIMITER $$
CREATE TRIGGER trg_roles_code
BEFORE INSERT ON Roles
FOR EACH ROW
BEGIN
    SET NEW.role_code = CONCAT('ROL', LPAD((SELECT IFNULL(MAX(id),0)+1 FROM Roles), 4, '0'));
END $$
DELIMITER ;

INSERT INTO Roles(role_name) VALUES
('ADMIN'), ('Developer'), ('Tester'), ('EMPLOYEE'), ('MANAGER');

select * from Roles;

-- users Main
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
	user_code VARCHAR(10) UNIQUE,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(20),
    country VARCHAR(100),
    state VARCHAR(100),
    city VARCHAR(100),
    postal_code VARCHAR(20),
    status VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DELIMITER $$
CREATE TRIGGER trg_users_code
BEFORE INSERT ON Users
FOR EACH ROW
BEGIN
    SET NEW.user_code = CONCAT('USE', LPAD((SELECT IFNULL(MAX(user_id),0)+1 FROM Users), 4, '0'));
END $$
DELIMITER ;

-- DROP TABLE IF EXISTS UserRequests;

CREATE TABLE UserRequests (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(20),
    country VARCHAR(100),
    state VARCHAR(100),
    city VARCHAR(100),
    postal_code VARCHAR(20),
    status VARCHAR(20) NOT NULL,
    approvedStatus VARCHAR(20) DEFAULT 'Created',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS Locations;

CREATE TABLE Locations (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL
);

-- DELETE FROM Locations WHERE location_id = 7;

-- ALTER TABLE Locations
-- MODIFY COLUMN country VARCHAR(100) NOT NULL,
-- MODIFY COLUMN state VARCHAR(100) NOT NULL,
-- MODIFY COLUMN city VARCHAR(100) NOT NULL;

 -- DROP TABLE IF EXISTS UserRoles;

CREATE TABLE UserRoles (
    user_id INT,
    role_id INT,
    location_id INT,
    PRIMARY KEY(user_id, role_id, location_id),
    FOREIGN KEY(user_id) REFERENCES Users(user_id),
    FOREIGN KEY(role_id) REFERENCES Roles(id),
    FOREIGN KEY(location_id) REFERENCES Locations(location_id)
);


 INSERT INTO Users(username, password, email, status, first_name, last_name)
 VALUES ('karthik', 'pass123', 'karthik@example.com', 'Active', 'Karthik', 'M');

select * from Users;
select * from UserRoles;
select * from Roles;
select * from Locations;

INSERT INTO Locations(country, state, city) VALUES
('BS', 'IN', 'Matthew Town'),
('BS', 'IN', 'New City');

SELECT user_code, username, password FROM Users WHERE user_code = 'USE0007';
select * from UserRequests;

INSERT INTO UserRoles(user_id, role_id, location_id) VALUES
(1, 1, 1),  
(1, 1, 2); 

-- 8. Query to fetch only codes
SELECT 
    u.user_code, 
    r.role_code, 
    r.role_name, 
    l.country, 
    l.city
FROM UserRoles ur
JOIN Users u ON ur.user_id = u.user_id
JOIN Roles r ON ur.role_id = r.id
JOIN Locations l ON ur.location_id = l.location_id;