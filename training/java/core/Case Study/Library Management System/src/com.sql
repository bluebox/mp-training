use bhanu;
show tables;
drop table books;
create table books(BookId int primary key,Title varchar(255) not null,Author varchar(255) not null,Category varchar(100) not null,Status char(1) not null,Availability char(1) not null);
desc books;
create table books_log(BookId int primary key,Title varchar(255) not null,Author varchar(255) not null,Category varchar(100) not null,Status char(1) not null,Availability char(1) not null);
desc books;
create table members(MemberId int primary key,Name varchar(255) not null,Email varchar(255) not null unique,Mobile int not null unique,Gender char(1) not null,Address varchar(255) not null);
create table members_log(MemberId int primary key,Name varchar(255) not null,Email varchar(255) not null unique,Mobile int not null unique,Gender char(1) not null,Address varchar(255) not null);
