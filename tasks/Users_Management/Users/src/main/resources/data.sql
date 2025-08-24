Create database org;
use org;

CREATE TABLE Roles (
    id INT PRIMARY KEY AUTO_INCREMENT, --active
    role_name VARCHAR(100) UNIQUE NOT NULL
);

insert into Roles(id,role_name) values (100,"ADMIN");
insert into Roles(id,role_name) values (101,"Developer");
insert into Roles(id,role_name) values (102,"Tester");
insert into Roles(id,role_name) values (103,"EMPLOYEE");
insert into Roles(id,role_name) values (104,"MANAGER");

select * from Roles;

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(20),
    country VARCHAR(100),
    state VARCHAR(100),
    city VARCHAR(100),
    postal_code VARCHAR(20),
    status VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE UserRequests (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(20),
    country VARCHAR(100),
    state VARCHAR(100),
    city VARCHAR(100),
    postal_code VARCHAR(20),
    status VARCHAR(20) NOT NULL,
    aprovedStatus VARCHAR(20) DEFAULT "Pending",
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );
    
select * from UserRequests;
select * from Users;

CREATE TABLE userroles (
id
	user_id INT,
    role_id INT,
    country VARCHAR(100),
	state VARCHAR(100),
    city VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
	FOREIGN KEY (role_id) REFERENCES Roles(id),
    primary key(user_id,role_id)
);

role_regions
id_

Insert into userroles values(1,100,'BS','IN','Matthew Town');
select * from userroles;
select * from Users;

-- drop table userroles;
-- drop table UserRequests;

Select ro.role_name  from Roles As ro WHERE id IN (SELECT role_id from userroles where user_id=1);
