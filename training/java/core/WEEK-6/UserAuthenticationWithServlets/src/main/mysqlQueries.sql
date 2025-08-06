-- Operation of user db
create database user;
use user;

create table users(
id int primary key auto_increment,
name varchar(50),
gmail varchar(50),
age int);

select * from users;

drop table users;

-- Operations of auth db
create schema auth;
use auth;

create table users(
gmail varchar(50) primary key,
password varchar(50) not null);

desc auth.users;
 
select * from auth.users;
delete from auth.users;
drop table auth.users;

