CREATE DATABASE library;
USE library;

SHOW TABLES;


CREATE TABLE books (
    BookId INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(50) NOT NULL,
    Author VARCHAR(50) NOT NULL,
    Category VARCHAR(10) NOT NULL,         
    Status CHAR(1) NOT NULL,          -- 'A' for Active, 'I' for Inactive (book-condition)
    Availablity CHAR(1) NOT NULL,     -- 'A' for Available, 'I' for Issued (book availability for issue)
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    created_by VARCHAR(50) NOT NULL DEFAULT 'SYSTEM',       
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,   
    updated_by VARCHAR(50) NULL,                            
    CONSTRAINT UQ_Title_Category UNIQUE (Title, Category)
);

insert into books(BookId, Title,Author,Category,Status,Availablity)
values(37,"k","kk","Comedy","A","A");

DESCRIBE books;

CREATE TABLE books_log (
    BookId INT NOT NULL,
    Title VARCHAR(50) NOT NULL,
    Author VARCHAR(50) NOT NULL,
    Category VARCHAR(50) NOT NULL,
    Status CHAR(1) NOT NULL,
    Availablity CHAR(1) NOT NULL,
    original_created_at DATETIME,
    original_created_by VARCHAR(50),
    original_updated_at DATETIME,
    original_updated_by VARCHAR(50),
    LogDate DATETIME NOT NULL
);

CREATE TABLE members (
    memberID INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    phoneNumber BIGINT NOT NULL UNIQUE,
    gender CHAR(1) NOT NULL,
    address VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50) NOT NULL DEFAULT 'SYSTEM',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NULL
);

CREATE TABLE members_log (
    MemberId INT NOT NULL,
    Name VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    PhoneNumber BIGINT,
    Gender CHAR(1),
    Address VARCHAR(100),
    original_created_at DATETIME,
    original_created_by VARCHAR(50),
    original_updated_at DATETIME,
    original_updated_by VARCHAR(50),
	LogDate DATETIME NOT NULL
);
select * from members;

CREATE TABLE issue_records (
    IssueId INT PRIMARY KEY AUTO_INCREMENT,
    BookId INT NOT NULL,
    MemberId INT NOT NULL,
    Status CHAR(1) NOT NULL,  -- 'I' for Issued and 'R' for Return
    IssueDate DATETIME NOT NULL,
    issued_by VARCHAR(50) NOT NULL DEFAULT 'SYSTEM',
    ReturnDate DATETIME NULL,
    returned_by VARCHAR(50) NULL,                   
    CONSTRAINT FK_IssueRecord_Book FOREIGN KEY (BookId) REFERENCES books(BookId), --  ON DELETE CASCADE,
    CONSTRAINT FK_IssueRecord_Member FOREIGN KEY (MemberId) REFERENCES members(memberID) -- ON DELETE CASCADE
);


CREATE TABLE issue_records_log (
    IssueId INT NOT NULL,                  
    BookId INT NOT NULL,
    MemberId INT NOT NULL,
    Status CHAR(1) NOT NULL, -- 'I' for Issued and 'R' for Return
    IssueDate DATETIME NOT NULL,
    issued_by VARCHAR(50) NOT NULL,
    ReturnDate DATETIME NULL,
    returned_by VARCHAR(50) NULL,
    LogDate DATETIME NOT NULL           
);

select * from books;
select * from members;
select * from issue_records;

select * from books_log;
select * from members_log;
select * from issue_records_log;

INSERT INTO issue_records (BookId, MemberId, Status, IssueDate, issued_by, ReturnDate, returned_by)
VALUES (37, 3, 'I', '2025-07-01 10:00:00', 'SYSTEM', NULL, NULL);


-- CREATE TABLE books (
--     BookId INT PRIMARY KEY AUTO_INCREMENT,
--     Title VARCHAR(255) NOT NULL,
--     Author VARCHAR(255) NOT NULL,
--     Category VARCHAR(100) NOT NULL,
--     Status CHAR(1) NOT NULL,      -- 'A' for Active, 'I' for Inactive
--     Availablity CHAR(1) NOT NULL,  -- 'A' for Available, 'I' for Issued
--     CONSTRAINT UQ_Title_Category UNIQUE (Title, Category)
-- );

-- CREATE TABLE members (
--     memberID INT PRIMARY KEY AUTO_INCREMENT,
--     name VARCHAR(255) NOT NULL,
--     email VARCHAR(255) UNIQUE,
--     phoneNumber BIGINT,
--     gender CHAR(1),
--     address VARCHAR(255)
-- );

-- CREATE TABLE issue_records (
--     IssueId INT PRIMARY KEY AUTO_INCREMENT,
--     BookId INT NOT NULL,
--     MemberId INT NOT NULL,
--     Status CHAR(1) NOT NULL,   
--     IssueDate DATETIME NOT NULL,
--     ReturnDate DATETIME NULL,        
--     FOREIGN KEY (BookId) REFERENCES books(BookId) ON DELETE CASCADE,
--     FOREIGN KEY (MemberId) REFERENCES members(memberID) ON DELETE CASCADE
-- );

-- CREATE TABLE books_log (
--     BookId INT NOT NULL,                  
--     Title VARCHAR(255) NOT NULL,
--     Author VARCHAR(255) NOT NULL,
--     Category VARCHAR(100) NOT NULL,
--     Status CHAR(1) NOT NULL,
--     Availablity CHAR(1) NOT NULL,
--     ChangeDate DATETIME NOT NULL,         
--     ChangeType VARCHAR(100) NOT NULL //remove
-- );

-- CREATE TABLE members_log (
--     LogId INT PRIMARY KEY AUTO_INCREMENT,  //remove 
--     MemberId INT NOT NULL,                  
--     Name VARCHAR(255) NOT NULL,
--     Email VARCHAR(255) NOT NULL,            
--     PhoneNumber BIGINT,                     
--     Gender CHAR(1),
--     Address VARCHAR(255),
--     ChangeDate DATETIME NOT NULL,    //rename       
--     ChangeType VARCHAR(100) NOT NULL    //remove    
-- );


-- ALTER TABLE books
-- ADD CONSTRAINT UQ_Title_Category UNIQUE (Title, Category);


-- ALTER TABLE issue_records
-- MODIFY COLUMN IssueDate DATETIME NOT NULL,
-- MODIFY COLUMN ReturnDate DATETIME NULL;

-- drop table issue_records;
-- drop table issue_records_log;
-- drop table books;
-- drop table members;
-- drop table books_log; 
-- drop table members_log;