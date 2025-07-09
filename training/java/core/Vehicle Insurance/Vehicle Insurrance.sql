use bhanu;
create table admin(
    username varchar(255) primary key not null,
	password varchar(255) not null
);
create table customers(
	customer_id int primary key auto_increment,
	name varchar(255) not null,
	email varchar(255) unique not null,
	contact varchar(15) unique not null,
	gender char CHECK (Gender IN ('M', 'F')) not null,
	age int not null,
    occupation varchar(255) not null,
	income float not null,
    address varchar(255) not null,
    status char CHECK (status IN ('A', 'I')) not null,
	customer_updated_on datetime not null,
    customer_updated_by varchar(255) not null,
    created_by varchar(255) not null,
    foreign key(created_by) references admin(username)
);
show tables;
create table users(
	username varchar(255) primary key not null,
	password varchar(255) not null,
    password_updated_on datetime not null,
    password_updated_by varchar(255) not null,
	customer_id int not null,foreign key(customer_id) references customers(customer_id)
);
create table vehicles(
	vehicle_id int primary key auto_increment,
    chasis_no int unique not null,
	reg_num varchar(255) unique not null,
	vehicle_model varchar(255) not null,
	purchase_date datetime not null,
    status char not null,
    vehicle_updated_on datetime not null,
    vehicle_updated_by varchar(255) not null,
	customer_id int not null,foreign key(customer_id) references customers(customer_id),
	created_by varchar(255) not null,
    foreign key(created_by) references admin(username)
);
create table policy(
	policy_id int primary key auto_increment,
	policy_term int not null,
	policy_type varchar(255) check(policy_type in ("silver","gold","platinum")),
	premium_amount double not null,
	policy_amount double not null,
    start_date datetime not null,
    end_date datetime not null,
	policy_status char check(policy_status in('R','A','I')),/*Requested,Active,Inactive*/
	vehicle_id int not null,foreign key(vehicle_id) references vehicles(vehicle_id),
    approved_by varchar(255) not null,
    foreign key(approved_by) references admin(username)
);
create table claim(
	claim_id int primary key auto_increment,
	req_amount double not null,
	damage_type varchar(255) not null,
	claim_status char check(claim_status in ('A','I','R')),/*Approved,Rejected,Initiated*/
	claim_date datetime,
	policy_id int not null,foreign key(policy_id) references policy(policy_id)
);

insert into vehicles values(2343,"dfw434","wer","18-06-12 10:34:09 AM","18-06-12 10:34:09 AM","Bhanu",1);
select * from vehicles; 

drop table claim;
drop table policy;
drop table vehicles;
