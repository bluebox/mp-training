CREATE DATABASE orga;
USE orga;

-- DROP TABLE IF EXISTS Roles;

CREATE TABLE Roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    role_code VARCHAR(10) UNIQUE,
    role_name VARCHAR(10) UNIQUE NOT NULL -- 10
);

select * from Roles;

show create table Roles;
describe Roles;

ALTER TABLE Roles
Modify COLUMN created_at  DATETIME ;

ALTER TABLE Roles
Modify COLUMN updated_at DATETIME ;

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
    username VARCHAR(15) UNIQUE NOT NULL, -- 15
    password VARCHAR(255) NOT NULL, 
    email VARCHAR(30) UNIQUE NOT NULL, -- 30
    first_name VARCHAR(20), -- 20
    last_name VARCHAR(20), -- 20 
    phone_number VARCHAR(10), -- 10
    country VARCHAR(3),
    state VARCHAR(3),
    city VARCHAR(20),
    postal_code VARCHAR(6), -- 6
    status VARCHAR(8) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);


show create table Users;
describe Users;

select * from Users;

-- DROP TRIGGER IF EXISTS trg_users_code;
-- DROP TRIGGER IF EXISTS tgr_generate_strong_password;

DELIMITER $$
CREATE TRIGGER trg_users_code
BEFORE INSERT ON Users
FOR EACH ROW
BEGIN
    SET NEW.user_code = CONCAT('USE', LPAD((SELECT IFNULL(MAX(user_id),0)+1 FROM Users), 4, '0'));
END $$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION generates_strong_password(length INT)
RETURNS VARCHAR(255)
NO SQL
BEGIN
    SET @chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@*';
    SET @password = '';
    SET @i = 0;
    WHILE @i < length DO
        SET @password = CONCAT(@password, SUBSTR(@chars, FLOOR(RAND() * LENGTH(@chars)) + 1, 1));
        SET @i = @i + 1;
    END WHILE;
    RETURN @password;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trg_generate_strong_password
BEFORE INSERT ON Users
FOR EACH ROW
BEGIN
    SET NEW.password = generates_strong_password(12);
END$$
DELIMITER ;


-- DROP TABLE IF EXISTS UserRequests;

CREATE TABLE UserRequests (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(15) UNIQUE NOT NULL, -- 15
    email VARCHAR(30) UNIQUE NOT NULL, -- 30
    first_name VARCHAR(20), -- 20
    last_name VARCHAR(20), -- 20
    phone_number VARCHAR(10), -- 10
    country VARCHAR(3),
    state VARCHAR(3),
    city VARCHAR(20),
    postal_code VARCHAR(6), -- 6
    status VARCHAR(8) NOT NULL,
    approvedStatus VARCHAR(10) DEFAULT 'Created',
    created_at DATETIME ,
    updated_at DATETIME
);

describe UserRequests;

show create table UserRequests;

select * from UserRequests;

DROP TABLE IF EXISTS Locations;

CREATE TABLE Locations (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(3) NOT NULL,
    state VARCHAR(3) NOT NULL,
    city VARCHAR(20) NOT NULL
);

select * from Locations;

describe Locations;

show create table Locations;

ALTER TABLE Locations
modify COLUMN created_at  DATETIME ;

ALTER TABLE Locations
modify COLUMN updated_at DATETIME ;

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


create table userrole like UserRoles;

insert into userrole select * from UserRoles;


select * from userrole;
select * from UserRoles;

show create table UserRoles;

ALTER TABLE UserRoles 
ADD COLUMN  status VARCHAR(10) NOT NULL Default 'ACTIVE';

ALTER TABLE UserRequests
Drop COLUMN  password ;

ALTER TABLE Users
ADD COLUMN  gender VARCHAR(7) ;

ALTER TABLE UserRoles
modify COLUMN created_at DATETIME;

 INSERT INTO Users(username, email, status, first_name, last_name, gender)
 VALUES ('karthik456', 'karthik456@example.com', 'ACTIVE', 'Karthik', 'M','MALE');

select * from UserRequests;
select * from Users;
select * from UserRoles;
select * from Roles;
select * from Locations;



-- INSERT INTO Locations(country, state, city) VALUES
-- ('BS', 'IN', 'Matthew Town'),
-- ('BS', 'IN', 'New City');
--
-- SELECT user_code, username, password FROM Users WHERE user_code = 'USE0007';

-- INSERT INTO UserRoles(user_id, role_id, location_id) VALUES
-- (1, 1, 1),  
-- (1, 1, 2); 

-- SELECT 
--    u.user_code, 
--    r.role_code, 
--    r.role_name, 
--    l.country, 
--    l.city
-- FROM UserRoles ur
-- JOIN Users u ON ur.user_id = u.user_id
-- JOIN Roles r ON ur.role_id = r.id
-- JOIN Locations l ON ur.location_id = l.location_id;