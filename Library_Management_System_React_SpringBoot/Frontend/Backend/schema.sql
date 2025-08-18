
CREATE TABLE IF NOT EXISTS books (
    BookId INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(255) NOT NULL,
    Author VARCHAR(255) NOT NULL,
    Category VARCHAR(100) NOT NULL,
    Status CHAR(1) NOT NULL,        
    Availability CHAR(1) NOT NULL   
);

CREATE TABLE IF NOT EXISTS books_log (
    LogId INT PRIMARY KEY AUTO_INCREMENT,
    BookId INT NOT NULL,
    Title VARCHAR(255) NOT NULL,
    Author VARCHAR(255) NOT NULL,
    Category VARCHAR(100) NOT NULL,
    Status CHAR(1) NOT NULL,
    Availability CHAR(1) NOT NULL,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS members (
    MemberId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Mobile BIGINT NOT NULL UNIQUE,
    Gender CHAR(1) NOT NULL,      
    Address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS members_log (
    LogId INT PRIMARY KEY AUTO_INCREMENT,
    MemberId INT NOT NULL,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Mobile BIGINT NOT NULL,
    Gender CHAR(1) NOT NULL,
    Address VARCHAR(255) NOT NULL,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS issue_records (
    IssueId INT PRIMARY KEY AUTO_INCREMENT,
    BookId INT NOT NULL,
    MemberId INT NOT NULL,
    Status CHAR(1) NOT NULL,       
    IssueDate DATE NOT NULL,
    ReturnDate DATE,
    FOREIGN KEY (BookId) REFERENCES books(BookId),
    FOREIGN KEY (MemberId) REFERENCES members(MemberId)
);

CREATE TABLE IF NOT EXISTS issue_records_log (
    LogId INT PRIMARY KEY AUTO_INCREMENT,
    IssueId INT NOT NULL,
    BookId INT NOT NULL,
    MemberId INT NOT NULL,
    Status CHAR(1) NOT NULL,
    IssueDate DATE NOT NULL,
    ReturnDate DATE,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
