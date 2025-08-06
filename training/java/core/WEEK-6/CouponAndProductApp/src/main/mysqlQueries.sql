create schema cap;
use cap;

-- creating coupon table
create table coupon(
id int primary key auto_increment,
code varchar(30) not null unique,
discount int check(discount between 1 and 100) not null,
expDate date);

-- creating product table
create table product(
id int primary key auto_increment,
name varchar(50) not null,
description varchar(255),
price double not null,
coupon_code varchar(30),
foreign key(coupon_code) references coupon(code));

-- queries for selecting rows of tables
select * from coupon;
select * from product;

-- queries for dropping tables
drop table product;
drop table coupon;

-- queries for deleting all rows 
delete from product;
delete from coupon;

