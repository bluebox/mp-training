create schema lms_updated;
use lms_updated;

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
email varchar(255) not null,
mobile char(10) not null check(length(mobile)=10),
gender char(1) not null check(gender in ('M','F')),
address varchar(255) not null,
status char(1) not null check(status in ('A','I')) default 'A');

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
address varchar(255),
status char(1));

-- Creating issue records log table
create table issue_records_log(
issue_id int,
book_id int,
member_id int,
status char(1),
issue_date date,
return_date date);

select * from books;
select * from books_log;
select * from members;
select * from members_log;
	
select * from issue_records_log;

alter table members add address varchar(255) not null;
alter table members drop column address;

alter table members modify mobile char(10) not null check(length(mobile)=10);
alter table members modify email varchar(255) not null;

drop table issue_records;
drop table members;