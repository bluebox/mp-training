create schema rms;
use rms;

create table employees(
emp_id int primary key auto_increment,
name varchar(255) not null,
email varchar(155) unique not null,
phone varchar(10) check(length(phone)=10) unique not null,
status varchar(10) check(status in ('Active','Inactive')) not null,
designation varchar(20) check(designation in ('Admin','Staff','Waiter')) not null,
join_date date not null,
leaving_date date);

create table customers(
cust_id int primary key auto_increment,
name varchar(255) not null,
phone varchar(10) check(length(phone)=10) unique not null);

create table waiters(
wtr_id int primary key auto_increment,
emp_id int not null,
availability varchar(20) check(availability in ('Available','Busy')) not null,
constraint fk_employees_staff foreign key(emp_id) references employees(emp_id));

create table orders(
ord_id int primary key auto_increment,
cust_id int not null,
wtr_id int not null,
ord_date date not null,
amount decimal(10,2) not null check(amount>0),
status varchar(20) check(status in ('Pending','Cancelled','Completed')) not null,
constraint fk_customers_orders foreign key(cust_id) references customers(cust_id),
constraint fk_waiters_orders foreign key(wtr_id) references waiters(wtr_id));

create table items(
item_id int primary key auto_increment,
name varchar(255) not null,
image varchar(155),
description varchar(255) not null,
price decimal(10,2) not null,
category varchar(55) not null,
availability varchar(20) check(availability in ('Available','Unavailable')) not null,
status varchar(20) check(status in ('Active','Inactive')) not null);

create table order_details(
ord_details_id int primary key auto_increment,
ord_id int not null,
item_id int not null,
quantity int not null check(quantity>0),
price decimal(10,2) not null check(price>0),
constraint fk_orders_order_details foreign key(ord_id) references orders(ord_id),
constraint fk_items_order_details foreign key(item_id) references items(item_id));

create table credentials(
username VARCHAR(50) NOT NULL PRIMARY KEY,
password VARCHAR(100) NOT NULL,
authority VARCHAR(50) check(authority in ('Admin','Staff')) NOT NULL,
enabled BOOLEAN NOT NULL);

create table waiters_log(
wtr_id int,
emp_id int,
availability varchar(20));

create table employee_log(
log_id int auto_increment primary key,
emp_id int ,
name varchar(255) not null,
email varchar(155) not null,
phone varchar(10) check(length(phone)=10)  not null,
status varchar(10) check(status in ('Active','Inactive')) not null,
designation varchar(20) check(designation in ('Admin','Staff','Waiter')) not null,
join_date date not null,
leaving_date date);

create table orders_log(
ord_id int,
cust_id int,
wtr_id int,
ord_date date,
amount decimal(10,2),
status varchar(20));

create table items_log(
item_id int,
name varchar(255),
image varchar(155),
description varchar(255),
price decimal(10,2),
category varchar(55),
availability varchar(20),
status varchar(20));




alter table items modify image varchar(155);

alter table items drop column status;

alter table items add availability varchar(20) check(availability in ('Available','Unavailable')) not null;
alter table items add status varchar(20) check(status in ('Active','Inactive')) not null;

desc items;

insert into credentials values('dummy','dummy','Admin',true);

alter table order_details modify price decimal(10,2) not null check(price>0);

alter table orders modify amount decimal(10,2) not null check(amount>0);

drop table employees;
drop table customers;
drop table waiters;
drop table orders;
drop table items;
drop table order_details;
drop table credentials;

alter table order_details drop constraint fk_orders_order_details;
alter table order_details drop constraint fk_items_order_details;

alter table order_details add constraint fk_orders_order_details foreign key(ord_id) references orders(ord_id);
alter table order_details add constraint fk_items_order_details foreign key(item_id) references items(item_id);

alter table employees modify designation varchar(20) check(designation in ('Admin','Staff','Waiter')) not null;
alter table employees drop constraint employees_chk_3;

select * from order_details;

select * from credentials;

insert into credentials values('admin','admin','Admin',true);
insert into credentials values('staff','staff','Staff',true);

select * from employees;
delete from credentials;

select * from waiters;
select * from waiters_log;

delete from waiters where wtr_id=13;

delete from employees;
delete from waiters;
delete from orders;
delete from items;
delete from order_details;

select * from customers;
alter table orders drop constraint orders_chk_1;
