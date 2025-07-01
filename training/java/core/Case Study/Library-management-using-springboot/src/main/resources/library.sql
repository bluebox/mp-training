use bhanu;
show tables;
drop table books;
create table books (
	BookId INT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Author VARCHAR(255) NOT NULL,
    Category varchar(255) Not null,
    Status CHAR(1) NOT NULL CHECK (Status IN ('A', 'I')),
    Availability CHAR(1) NOT NULL CHECK (Availability IN ('A', 'I'))
);
CREATE TABLE members (
    MemberId INT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Mobile BIGINT NOT NULL UNIQUE,
    Gender CHAR(1) NOT NULL CHECK (Gender IN ('M', 'F')),
    Address VARCHAR(255) NOT NULL
);
CREATE TABLE issue_records (
    IssueId INT PRIMARY KEY,
    BookId INT NOT NULL,
    MemberId INT NOT NULL,
    Status CHAR(1) NOT NULL CHECK (Status IN ('I', 'R')),
    IssueDate DATE NOT NULL,
    ReturnDate DATE,
    FOREIGN KEY (BookId) REFERENCES books(BookId),
    FOREIGN KEY (MemberId) REFERENCES members(MemberId)
);
select * from books;
select * from members;
select * from issue_records;
desc books;
ALTER TABLE books MODIFY BookId numeric PRIMARY KEY;
drop table issue_records;