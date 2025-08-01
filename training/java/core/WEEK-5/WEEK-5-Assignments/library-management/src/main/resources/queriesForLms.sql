-- Creating book table
create table books(
book_id int primary key auto_increment,
title varchar(255) not null,
author varchar(255) not null,
category varchar(100) not null,
status char(1) not null check(status in ('A','I')) default 'A',
availability char(1) not null check(availability in ('A','I')) default 'A');

-- creating members table
create table members(
member_id int primary key auto_increment,
name varchar(255) not null,
email varchar(255) not null unique,
mobile char(10) not null unique check(length(mobile)=10),
gender char(1) not null check(gender in ('M','F')),
address varchar(255) not null);

-- creating issue_records table
create table issue_records(
issue_id int primary key auto_increment,
book_id int not null,
member_id int not null,
status char(1) not null check(status in ('I','R')),
issue_date date not null,
return_date date,
constraint fk_issue_records_books foreign key(book_id) references books(book_id) on delete cascade,
constraint fk_issue_records_members foreign key(member_id) references members(member_id) on delete cascade);

-- Creating books log table
create table books_log(
book_id int,
title varchar(255),
author varchar(255),
category varchar(100),
status char(1),
availability char(1));

-- Creating members log table
create table members_log(
member_id int,
name varchar(255),
email varchar(255),
mobile char(10),
gender char(1),
address varchar(255));

-- Creating issue records log table
create table issue_records_log(
issue_id int,
book_id int,
member_id int,
status char(1),
issue_date date,
return_date date);

-- dropping all tablesbooks
drop table books_log;
drop table members_log;
drop table issue_records_log;
drop table issue_records;
drop table members;
drop table books;

-- Selection queries
select * from lms.books;
select * from lms.books_log;
select * from lms.members;
select * from lms.members_log;
select * from lms.issue_records;
select * from lms.issue_records_log;

-- deleting all rows queries
delete from lms.books;
delete from lms.books_log;
delete from lms.members;
delete from lms.members_log;
delete from lms.issue_records;
delete from lms.issue_records_log;

alter table lms.members modify mobile char(10);
desc lms.members;
alter table lms.members_log modify mobile char(10);
desc lms.members_log;

-- Selecting member books by member id
insert into lms.issue_records(book_id,member_id,status,issue_date) values(4,1,'I','2025-07-25');
insert into lms.issue_records(book_id,member_id,status,issue_date) values(4,2,'I','2025-07-25');
select b.* from books b join issue_records i on b.book_id=i.book_id join members m on i.member_id=m.member_id where i.member_id=2;

-- creating lms_test schema and using it
create schema lms_test;
use lms_test;

-- using lms
use lms;

-- selecting test schema tables
select * from books;
select * from books_log;
select * from members;
select * from members_log;
select * from issue_records;
select * from issue_records_log;

-- deleting all table rows
delete from books;
delete from books_log;
delete from members;
delete from members_log;
delete from issue_records;
delete from issue_records_log;

delete from books where book_id=4;