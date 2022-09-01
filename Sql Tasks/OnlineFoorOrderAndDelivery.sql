create database OnlineFoodOrderAndDelivery;
use OnlineFoodOrderAndDelivery;

create table restaurant(
restaurant_id int primary key,
restaurant_name varchar(30),
restaurant_location varchar(50),
restaurant_rating decimal(2,1)
);

create table app_employee(
emp_id int primary key,
emp_name varchar(30),
emp_contact_number varchar(20) NOT NULL,
emp_avg_rating decimal(2,1)
);

create table customer(
customer_id int primary key,
customer_name varchar(30),
customer_number varchar(20),
customer_address varchar(50)
);

create table food(
food_id int primary key,
food_name varchar(20),
price decimal(5,2)
);



create table order_details(
order_id int primary key,
customer_id int,
restaurant_id int,
emp_id int,
order_status varchar(10),
order_time timestamp,
delivered_time timestamp,
CONSTRAINT fk_customer_id foreign key(customer_id) REFERENCES customer(customer_id) ON DELETE SET NULL ON UPDATE SET NULL,  
CONSTRAINT fk_restaurant_id foreign key(restaurant_id) REFERENCES restaurant(restaurant_id) ON DELETE SET NULL ON UPDATE SET NULL,
CONSTRAINT fk_emp_id foreign key(emp_id) REFERENCES app_employee(emp_id) ON DELETE SET NULL ON UPDATE SET NULL
);

create table payment(
transaction_id int primary key,
order_id int,
payment_type varchar(20),
payment_status varchar(20),
CONSTRAINT foreign key(order_id) REFERENCES order_details(order_id) ON DELETE SET NULL ON UPDATE SET NULL
);


create table order_food(
order_food_id int auto_increment primary key,
order_id int,
transaction_id int,
customer_id int,
restaurant_id int,
food_id int,
quantity int,
emp_id int,
CONSTRAINT fk_order_id foreign key(order_id) REFERENCES order_details(order_id) ON DELETE SET NULL ON UPDATE SET NULL, 
CONSTRAINT fk_payment_id foreign key(transaction_id) REFERENCES payment(transaction_id) ON DELETE SET NULL ON UPDATE SET NULL, 
CONSTRAINT foreign key(customer_id) REFERENCES customer(customer_id) ON DELETE SET NULL ON UPDATE SET NULL, 
CONSTRAINT foreign key(restaurant_id) REFERENCES restaurant(restaurant_id) ON DELETE SET NULL ON UPDATE SET NULL,
CONSTRAINT fk_food_id foreign key(food_id) REFERENCES food(food_id) ON DELETE SET NULL ON UPDATE SET NULL,
CONSTRAINT foreign key(emp_id) REFERENCES app_employee(emp_id) ON DELETE SET NULL ON UPDATE SET NULL
);

ALTER TABLE order_food AUTO_INCREMENT=0;

