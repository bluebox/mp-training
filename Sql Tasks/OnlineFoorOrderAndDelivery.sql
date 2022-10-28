create database OnlineFoodOrderAndDelivery;
use OnlineFoodOrderAndDelivery;

create table restaurant(
restaurant_id varchar(10) primary key,
restaurant_name varchar(30) not null,
restaurant_location varchar(50) not null,
restaurant_rating decimal(2,1),
restaurant_username varchar(30) unique not null,
restaurant_password varchar(20) not null,
phone varchar(20)
);


create table app_employee(
emp_id varchar(10) primary key,
emp_name varchar(30) not null,
emp_contact_number varchar(20) NOT NULL,
emp_avg_rating decimal(2,1),
emp_username varchar(30) unique not null,
emp_password varchar(20) not null,
emp_mail varchar(30) unique,
is_available boolean
);

create table customer(
customer_id varchar(10) primary key,
customer_name varchar(30) not null,
customer_number varchar(20) not null,
customer_address varchar(50) not null,
customer_username varchar(30) unique not null,
customer_password varchar(20) not null,
customer_mail varchar(30) unique
);

create table food(
food_id varchar(10) primary key,
food_name varchar(20) not null,
price decimal(5,2) not null,
food_photo varchar(30)
);

create table food_available(
restaurant_id varchar(10),
food_id varchar(10),
food_status varchar(10),
CONSTRAINT foreign key(restaurant_id) REFERENCES restaurant(restaurant_id) ON DELETE SET NULL ON UPDATE SET NULL,
CONSTRAINT foreign key(food_id) REFERENCES food(food_id) ON DELETE SET NULL ON UPDATE SET NULL
);


create table order_food(
order_food_id varchar(10) primary key,
customer_id varchar(10),
restaurant_id varchar(10),
food_id varchar(10),
quantity int not null,
emp_id varchar(10),

CONSTRAINT foreign key(customer_id) REFERENCES customer(customer_id) ON DELETE SET NULL ON UPDATE SET NULL, 
CONSTRAINT foreign key(restaurant_id) REFERENCES restaurant(restaurant_id) ON DELETE SET NULL ON UPDATE SET NULL,
CONSTRAINT fk_food_id foreign key(food_id) REFERENCES food(food_id) ON DELETE SET NULL ON UPDATE SET NULL,
CONSTRAINT foreign key(emp_id) REFERENCES app_employee(emp_id) ON DELETE SET NULL ON UPDATE SET NULL
);

create table payment(
transaction_id varchar(10) primary key,
order_food_id varchar(10),
payment_type varchar(20),
payment_status varchar(20),
CONSTRAINT fk_orderfood foreign key(order_food_id) REFERENCES order_food(order_food_id) ON DELETE SET NULL ON UPDATE SET NULL
);



create table order_details(
order_id varchar(10) primary key,
order_food_id varchar(10),
customer_id varchar(10),
restaurant_id varchar(10),
emp_id varchar(10),
transaction_id varchar(10),
order_status varchar(10),
order_time timestamp,
delivered_time timestamp,
CONSTRAINT foreign key(order_food_id) REFERENCES order_food(order_food_id) ON DELETE SET NULL ON UPDATE SET NULL,
CONSTRAINT fk_customer_id foreign key(customer_id) REFERENCES customer(customer_id) ON DELETE SET NULL ON UPDATE SET NULL,  
CONSTRAINT fk_restaurant_id foreign key(restaurant_id) REFERENCES restaurant(restaurant_id) ON DELETE SET NULL ON UPDATE SET NULL,
CONSTRAINT fk_emp_id foreign key(emp_id) REFERENCES app_employee(emp_id) ON DELETE SET NULL ON UPDATE SET NULL,
CONSTRAINT fk_transaction foreign key(transaction_id) REFERENCES payment(transaction_id) ON DELETE SET NULL ON UPDATE SET NULL
);






drop table customer;
drop table app_employee;
drop table restaurant;
drop table payment;
drop table food;
drop table order_details;
drop table order_food;
drop table food_available;

-- insert into restaurant values('r001','Homely','Madhapur',9.7,'homely','1234');
-- insert into restaurant values('r002','Chichas','Madhapur',7.2,'chichas','3242');
-- insert into restaurant values('r003','Ram ki Bandi','Charminar',9.0,'ramkibandi','8734');
-- insert into restaurant values('r004','Mc Donalds','Madhapur',8.1,'mcd','6812');

-- select * from restaurant;

-- insert into customer values('c001','Purnima Agarwal','112135353','Madhapur','purnima001','53553','puri@gmail.com');
-- insert into customer values('c002','yatin Vohra','112135353','Madhapur','vohrayatin','6756757','yatin@gmail.com');
-- insert into customer values('c003','Md Irfan','112135353','kondapur','irfan001','908w287','irfan@gmail.com');
-- insert into customer values('c004','Sakshi Dubey','112135353','Madhapur','sakshi002','f4534','sakshi@gmail.com');
-- insert into customer values('c005','Nikita Singh','112135353','Erragdda','nikita001','j76778','nikita@gmail.com');

-- select * from customer;

-- insert into app_employee values('e001','Sandeep Kumar','24535353',8.6,'sandeep001','dfwet54','sandeep@gmail.com');
-- insert into app_employee values('e002','Trisha Agarwal','565436353',9.1,'trisha001','jhdwegd77','trisha@gmail.com');
-- insert into app_employee values('e003','Anwesha Chakraborty','98765231',7.9,'anwesha001','wefji12','anwesha@gmail.com');

-- select * from app_employee;

-- insert into food values('f001','chowmein',200);
-- insert into food values('f002','momo',120);
-- insert into food values('f003','Naan',100);
-- insert into food values('f004','Paneer Butter Masala',400);

-- select * from food;

-- insert into order_details values('o001','c001','r001','e001','preparing','2022-08-19 03:14:07','2022-08-19 04:14:07');
-- insert into order_details values('o002','c003','r002','e001','cooked','2022-02-19 03:02:07','2022-02-19 04:14:07');

-- select * from order_details;

-- insert into payment values('t001','o001','cash','done');
-- insert into payment values('t002','o002','upi','pending');

-- select * from payment;




