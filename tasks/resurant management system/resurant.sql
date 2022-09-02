create database restaurant;
use restaurant;
create table customer(customer_id int primary key,
 customer_name varchar(50) not null,
 customer_address varchar(50) not null,
 phone_no int,
table_no int not null,
FOREIGN KEY (table_no) REFERENCES table_details(table_no),
 );
 

 create table employee(emp_id int primary key,
 name varchar(50) not null,
 job_type varchar(50) not null,
 date_of_joining int not null,
 type_of_employee varchar(50) not null
 );
 

create table table_details(
 table_no int primary key,
 size int not null,
 status_of_table int not null,
 waiter_id int not null,
 foreign key (waiter_id) references waiter(waiter_id)
 );
 


 
 create table chef(chef_id int primary key,
 chef_name varchar(50) not null,
 salary int not null,
 emp_id int not null,
 foreign key (emp_id) references employee(emp_id)
 ); 
 
 
  create table menu(meal_id int primary key,
 name varchar(50) not null,
 price float not null,
 chef_id int not null,
 foreign key (chef_id) references chef(chef_id)
 );
 
 
create table orders(
customer_id int NOT NULL,
meal_id int NOT NULL,
foreign key (meal_id) references menu(meal_id),
foreign key (customer_id) references customer(customer_id)
 );
 
 
  create table bills(
  transaction_id int primary key,
  customer_id int NOT NULL,
  Amount float NOT NULL,
  payment_mode varchar(50),
  bill_date datetime not null,
  foreign key (customer_id) references customer(customer_id)
  );


create table waiter(waiter_id int primary key,
 waiter_name varchar(50) not null,
 salary float not null,
 phone_no int not null,
 emp_id int not null,
 foreign key (emp_id) references employee(emp_id)
 );

 


 
