create database hospital;
use hospital;

create table roles(roleId varchar(10) primary key, roleName varchar(50) );



create table address( addr_id varchar(10) primary key , 
 pincode varchar(6),
 city varchar(50) ,
 state varchar(50),
 country varchar(50)
 );
 
 
create table users( userId varchar(10) primary key , 
firstName varchar(50) not null,
lastName varchar(50),
gender varchar(10) not null , 
mobileNumber varchar(12) not null,
addr_id varchar(10) ,
constraint  foreign key( addr_id)references address(addr_id) );
 

 
	create table role_user( user_role_id int auto_increment primary key,
	salary int not null , 
	designation varchar(50) not null , 
	created Date not null,
	userId varchar(10) ,
	roleId varchar(10) , 
	constraint  foreign key( userId)references users(userId) ,
	constraint  foreign key( roleId)references roles(roleId) );


create table room(
room_no int primary key,
roomType varchar(40),
status varchar(20)
);


create table patient(
patient_id varchar(10) primary key,
patient_name varchar(50) not null,
age int not null,
gender varchar(10) not null ,
registed_on date,
addr_id varchar(10) ,
constraint  foreign key( addr_id)references address(addr_id) 
);


create table inpatient(
id int primary key auto_increment,
disease varchar(50),
patient_id varchar(10),
room_no int,
doctor_id int ,
nurse_id int,
date_of_admission date not null,
date_of_discharge date ,
constraint  foreign key( patient_id)references patient(patient_id) ,
constraint  foreign key( room_no)references room(room_no),
constraint  foreign key( doctor_id)references role_user(user_role_id) ,
constraint  foreign key( nurse_id)references role_user(user_role_id) 
);
drop table inpatient;

create table outpatient(
id int primary key auto_increment,
disease varchar(50),
patient_id varchar(10),
date_of_treatment date not null ,
doctor_id int ,
nurse_id int,
constraint  foreign key( patient_id)references patient(patient_id) ,
constraint  foreign key( doctor_id)references role_user(user_role_id) ,
constraint  foreign key( nurse_id)references role_user(user_role_id)
);

create table bill(
bill_no int primary key auto_increment,
patient_id varchar(10),
doctor_charge int,
room_charge int,
num_of_days int,
generated_on date not null,
constraint  foreign key( patient_id)references patient(patient_id) 
);



