create database if not exists library;
use library;
CREATE TABLE books (
    bookId INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) UNIQUE NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(100) NOT NULL,          
    status CHAR(1) NOT NULL,             
    availability CHAR(1) NOT NULL,     
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100) NOT NULL,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(100) NULL
);

CREATE TABLE books_log (
    logId INT AUTO_INCREMENT PRIMARY KEY,
    bookId INT NOT NULL,
    title VARCHAR(100),
    author VARCHAR(100),
    category VARCHAR(100),
    status CHAR(1) NOT NULL,             
    availability CHAR(1) NOT NULL, 
    original_created_at TIMESTAMP,
    original_created_by VARCHAR(100),
    original_updated_at TIMESTAMP NULL,
    original_updated_by VARCHAR(100) NULL,
    logDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE members (
    memberID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phoneNumber BIGINT UNIQUE NOT NULL,
    gender CHAR(1) NOT NULL,
    address VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NULL
);

CREATE TABLE members_log (
    logId INT AUTO_INCREMENT PRIMARY KEY,
    memberId INT NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100),
    phoneNumber BIGINT,
    gender CHAR(1),
    address VARCHAR(500),
    logDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE issue_records (
    issueId INT AUTO_INCREMENT PRIMARY KEY,
    bookId INT NOT NULL,
    memberId INT NOT NULL,
    status VARCHAR(50) NOT NULL,         
    issueDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    issued_by VARCHAR(100) NOT NULL,
    returnDate TIMESTAMP NULL,
    returned_by VARCHAR(100) NULL,
    FOREIGN KEY (bookId) REFERENCES books(bookId) ON DELETE CASCADE,
    FOREIGN KEY (memberId) REFERENCES members(memberID) ON DELETE CASCADE,
    UNIQUE (bookId,memberId)
);


CREATE TABLE issue_records_log (
    logId INT AUTO_INCREMENT PRIMARY KEY,
    issueId INT NOT NULL,
    bookId INT NOT NULL,
    memberId INT NOT NULL,
    status VARCHAR(100),
    issueDate TIMESTAMP,
    issued_by VARCHAR(100),
    returnDate TIMESTAMP NULL,
    returned_by VARCHAR(100) NULL,
    logDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (issueId) REFERENCES issue_records(issueId),
    FOREIGN KEY (bookId) REFERENCES books(bookId),
    FOREIGN KEY (memberId) REFERENCES members(memberID) 
);

