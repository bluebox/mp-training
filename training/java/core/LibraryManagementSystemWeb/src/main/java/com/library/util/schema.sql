CREATE TABLE Books (
    BookId INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(255) NOT NULL,
    Author VARCHAR(255) NOT NULL,
    Category VARCHAR(100) NOT NULL,
    Status CHAR(1) NOT NULL CHECK (Status IN ('A', 'I')),
    Availability CHAR(1) NOT NULL CHECK (Availability IN ('A', 'I'))
);
CREATE TABLE books_log (
    book_id INT,
    title VARCHAR(255),
    author VARCHAR(255),
    category VARCHAR(100),
    status CHAR(1),     
    availability CHAR(1),  
    action_type VARCHAR(10), 
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE members (
    MemberId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Mobile BIGINT NOT NULL UNIQUE,
    Gender CHAR(1) NOT NULL CHECK (Gender IN ('M', 'F')),
    Address VARCHAR(255) NOT NULL
);
CREATE TABLE members_log (
    MemberId INT,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Mobile BIGINT NOT NULL,
    Gender CHAR(1) NOT NULL CHECK (Gender IN ('M', 'F')),
    Address VARCHAR(255) NOT NULL,
    LogAction VARCHAR(10) NOT NULL,        -- 'UPDATE' or 'DELETE'
    LogTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE issue_records (
    IssueId INT PRIMARY KEY AUTO_INCREMENT,
    BookId INT NOT NULL,
    MemberId INT NOT NULL,
    Status CHAR(1) NOT NULL CHECK (Status IN ('I', 'R')),
    IssueDate DATE NOT NULL,
    ReturnDate DATE,
    FOREIGN KEY (BookId) REFERENCES books(BookId),
    FOREIGN KEY (MemberId) REFERENCES members(MemberId)
);
CREATE TABLE issue_records_log (
    IssueId INT,
    BookId INT,
    MemberId INT,
    Status CHAR(1),
    IssueDate DATE,
    ReturnDate DATE
);





