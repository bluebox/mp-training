-- Create Database
CREATE DATABASE IF NOT EXISTS lms_db;

-- Use the database
USE lms_db;

-- Create 'members' table
CREATE TABLE IF NOT EXISTS members (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile VARCHAR(15) unique NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address varchar(150)  NOT NULL
);


CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,          -- Internal numeric ID
    book_id VARCHAR(10) UNIQUE,                 -- External formatted ID (B0000001)
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    status CHAR(1) NOT NULL,                    -- 'A' (Active) or 'I' (Inactive)
    availability CHAR(1) NOT NULL               -- 'A' (Available) or 'I' (Issued)
);
DELIMITER //

CREATE TRIGGER trg_generate_book_id
BEFORE INSERT ON books
FOR EACH ROW
BEGIN
    DECLARE nextId INT;

    -- Get the next AUTO_INCREMENT value from metadata
    SELECT AUTO_INCREMENT INTO nextId
    FROM information_schema.TABLES
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'books';

    -- Format as B0000001
    SET NEW.book_id = CONCAT('B', LPAD(nextId, 4, '0'));
END;
//

DELIMITER ;

DROP TABLE IF EXISTS issue_books;

CREATE TABLE issue_books (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    book_id VARCHAR(10) NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE NOT NULL,              -- expected due date
    actual_return_date DATE DEFAULT NULL,   -- real return date (NULL if not yet returned)
    CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES members(member_id),
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(book_id)
);



USE lms_db;


CREATE TABLE IF NOT EXISTS books_log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id VARCHAR(10),
    title VARCHAR(255),
    author VARCHAR(255),
    category VARCHAR(100),
    status CHAR(1),
    availability CHAR(1),
    action_type VARCHAR(10), -- INSERT, UPDATE, DELETE
    action_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS members_log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT,
    name VARCHAR(100),
    email VARCHAR(100),
    mobile VARCHAR(15),
    gender VARCHAR(10),
    address VARCHAR(150),
    action_type VARCHAR(10), -- INSERT, UPDATE, DELETE
    action_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS issue_books_log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    issue_id INT,
    member_id INT,
    book_id VARCHAR(10),
    issue_date DATE,
    return_date DATE,
    action_type VARCHAR(10), -- ISSUE, RETURN, DELETE
    action_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DELIMITER //

CREATE TRIGGER trg_books_insert
AFTER INSERT ON books
FOR EACH ROW
BEGIN
    INSERT INTO books_log (book_id, title, author, category, status, availability, action_type)
    VALUES (NEW.book_id, NEW.title, NEW.author, NEW.category, NEW.status, NEW.availability, 'INSERT');
END;
//

CREATE TRIGGER trg_books_update
AFTER UPDATE ON books
FOR EACH ROW
BEGIN
    INSERT INTO books_log (book_id, title, author, category, status, availability, action_type)
    VALUES (OLD.book_id, OLD.title, OLD.author, OLD.category, OLD.status, OLD.availability, 'UPDATE');
END;
//

CREATE TRIGGER trg_books_delete
AFTER DELETE ON books
FOR EACH ROW
BEGIN
    INSERT INTO books_log (book_id, title, author, category, status, availability, action_type)
    VALUES (OLD.book_id, OLD.title, OLD.author, OLD.category, OLD.status, OLD.availability, 'DELETE');
END;
//

CREATE TRIGGER trg_members_insert
AFTER INSERT ON members
FOR EACH ROW
BEGIN
    INSERT INTO members_log (member_id, name, email, mobile, gender, address, action_type)
    VALUES (NEW.member_id, NEW.name, NEW.email, NEW.mobile, NEW.gender, NEW.address, 'INSERT');
END;
//

CREATE TRIGGER trg_members_update
AFTER UPDATE ON members
FOR EACH ROW
BEGIN
    INSERT INTO members_log (member_id, name, email, mobile, gender, address, action_type)
    VALUES (OLD.member_id, OLD.name, OLD.email, OLD.mobile, OLD.gender, OLD.address, 'UPDATE');
END;
//

CREATE TRIGGER trg_members_delete
AFTER DELETE ON members
FOR EACH ROW
BEGIN
    INSERT INTO members_log (member_id, name, email, mobile, gender, address, action_type)
    VALUES (OLD.member_id, OLD.name, OLD.email, OLD.mobile, OLD.gender, OLD.address, 'DELETE');
END;
//

CREATE TRIGGER trg_issue_books_insert
AFTER INSERT ON issue_books
FOR EACH ROW
BEGIN
    INSERT INTO issue_books_log (issue_id, member_id, book_id, issue_date, return_date, action_type)
    VALUES (NEW.issue_id, NEW.member_id, NEW.book_id, NEW.issue_date, NEW.return_date, 'ISSUE');
END;
//

CREATE TRIGGER trg_issue_books_update
AFTER UPDATE ON issue_books
FOR EACH ROW
BEGIN
    INSERT INTO issue_books_log (issue_id, member_id, book_id, issue_date, return_date, action_type)
    VALUES (NEW.issue_id, NEW.member_id, NEW.book_id, NEW.issue_date, NEW.return_date, 'RETURN');
END;
//

CREATE TRIGGER trg_issue_books_delete
AFTER DELETE ON issue_books
FOR EACH ROW
BEGIN
    INSERT INTO issue_books_log (issue_id, member_id, book_id, issue_date, return_date, action_type)
    VALUES (OLD.issue_id, OLD.member_id, OLD.book_id, OLD.issue_date, OLD.return_date, 'DELETE');
END;
//

DELIMITER ;
