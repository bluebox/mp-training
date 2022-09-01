create database hospital;
use hospital;

create table roles(roleId varchar(10) primary key, roleName varchar(50) );
drop table roles;
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
 
 