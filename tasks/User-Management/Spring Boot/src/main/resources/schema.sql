create database IF NOT EXISTS userManagement;
use userManagement;

create table users (
    userName varchar(100) PRIMARY KEY,
    password varchar(255) NOT NULL,
    firstName varchar(100) NOT NULL,
    lastName varchar(100) NOT NULL,
    email varchar(100) UNIQUE NOT NULL,
    phoneNumber bigint UNIQUE NOT NULL,
    gender char(1) NOT NULL,
    country varchar(100) NOT NULL,
    state varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    pinCode int NOT NULL,
    status char(1) NOT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by varchar(100) NOT NULL DEFAULT 'ADMIN',
    updated_at datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by varchar(100) DEFAULT NULL
);

create table user_requests (
    requestId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName varchar(100) NOT NULL,
    lastName varchar(100) NOT NULL,
    email varchar(100) UNIQUE NOT NULL,
    phoneNumber bigint UNIQUE NOT NULL,
    gender char(1) NOT NULL,
    country varchar(100) NOT NULL,
    state varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    pinCode int NOT NULL,
    approval char(1) NOT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by varchar(100) NOT NULL DEFAULT 'ADMIN',
    updated_at datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by varchar(100) DEFAULT NULL
);

create table roles (
    roleId int PRIMARY KEY,
    roleName varchar(10) NOT NULL UNIQUE,
    status char(1) NOT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by varchar(100) NOT NULL DEFAULT 'ADMIN',
    updated_at datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by varchar(100) DEFAULT NULL
);

insert into roles(roleId,roleName,status) values 
    (100,"ADMIN","A"),
    (101,"DEVELOPER","A"),
    (102,"TESTER","A"),
    (103,"EMPLOYEE","A"),
    (104,"MANAGER","A");

CREATE TABLE user_roles (
    userRoleId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userName varchar(100) NOT NULL,
    roleId INT NOT NULL,
    country varchar(100) NOT NULL,
    state varchar(100) NOT NULL,
    city varchar(100) NOT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by varchar(100) NOT NULL DEFAULT 'ADMIN',
    updated_at datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by varchar(100) DEFAULT NULL,
    FOREIGN KEY (userName) REFERENCES users(userName),
    FOREIGN KEY (roleId) REFERENCES roles(roleId),
    UNIQUE (userName, roleId)
);

CREATE TABLE conflicting_roles (
	roleIdA INT NOT NULL,
    roleIdB INT NOT NULL,
    UNIQUE(roleIdA, roleIdB)
);
insert into conflicting_roles values (101,102);
insert into conflicting_roles values (103,104);
