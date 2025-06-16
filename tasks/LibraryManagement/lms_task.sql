create database lms_task;
use lms_task;
create table books(
bookid int primary key auto_increment,
title varchar(255) not null,
author varchar(255) not null,
category varchar(100) not null,
status char(1) not null check (status in ('A','I')),
availability char(1) not null check (availability in ('A','I'))
);
CREATE TABLE books_log (
    bookid INT,
    title VARCHAR(255),
    author VARCHAR(255),
    category VARCHAR(100),
    status CHAR(1),
    availability CHAR(1)
);
CREATE TABLE members (
    memberid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mobile BIGINT NOT NULL UNIQUE,
    gender CHAR(1) NOT NULL CHECK (Gender IN ('M', 'F'))
);
CREATE TABLE members_log (
    memberid INT,
    name VARCHAR(255),
    email VARCHAR(255),
    mobile BIGINT,
    gender CHAR(1)
);
CREATE TABLE issue_records (
    issueid INT PRIMARY KEY AUTO_INCREMENT,
    bookid INT NOT NULL,
    memberid INT NOT NULL,
    status CHAR(1) NOT NULL CHECK (Status IN ('I', 'R')),
    issuedate DATE NOT NULL,
    returndate DATE,
    FOREIGN KEY (bookid) REFERENCES books(bookid),
    FOREIGN KEY (memberid) REFERENCES members(memberid)
);
CREATE TABLE issue_records_log (
    issueid INT,
    bookid INT,
    memberid INT,
    status CHAR(1),
    issuedate DATE,
    returndate DATE
);
show tables;