show databases;
create database if not exists EventManagement;
use EventManagement;
show tables;

create table users(
user_id int,
name varchar(32) not null,
phn_number varchar(32) not null,
email varchar(32) not null unique,
role varchar(32) not null,
gender varchar(1) not null,
status varchar(1) not null,  -- A-Active-A , I-Inactive
dept varchar(32),
primary key(user_id)
);

drop table eventCreation;

create table eventCreation(
event_id int,
name varchar(32) not null,
start_date datetime not null,
end_date datetime not null,
venue varchar(252) not null,
event_organization varchar(64) not null,
event_capacity int not null,
participant_count int default 0,
event_status varchar(1) default 'A' not null, -- A-Active , F-Finished , C-Cancelled
created_by int not null,
created_at datetime,
updated_by int,
updated_at datetime,

primary key(event_id),
foreign key (created_by) REFERENCES users(user_id),
foreign key (updated_by) REFERENCES users(user_id)
);

desc eventCreation;

create table eventRegistration(
user_id int,
event_id int,
registration_status varchar(1) Default 'R' not null, -- A-Attended , R-Registered , C-Cancelled , N-NotAttended
registered_by int not null,
registered_at datetime not null,
updated_by int,
updated_at datetime,
CONSTRAINT registered_id PRIMARY KEY (user_id,event_id),
foreign key (user_id) references users(user_id),
foreign key (event_id) references eventCreation(event_id)
);

desc eventRegistration;

create table feedback(
user_id int,
event_id int,
rating  int not null,
feedback varchar(256),
check(rating<=10 && rating>=1),
CONSTRAINT feedback_id PRIMARY KEY (user_id,event_id),
foreign key (user_id) references users(user_id),
foreign key (event_id) references eventCreation(event_id)
);

desc feedback;

create table credentials(
user_id int,
password varchar(32) not null,
created_by int not null,
created_at datetime not null,
updated_by int,
updated_at datetime,
primary key(user_id),
foreign key (user_id) references users(user_id)
);
desc credentials;

-- Event Creation
desc eventCreation;

ALTER TABLE eventCreation
ADD COLUMN created_at datetime null
AFTER created_by; 

ALTER TABLE eventCreation
DROP COLUMN updated_by; 

ALTER TABLE eventCreation
DROP COLUMN updated_at; 

INSERT INTO eventCreation(event_id,name,start_date,end_date,venue,event_organization,
	event_capacity,participant_count,event_status,created_by,created_at,updated_by,updated_at) 
	values (1,'Dev','2025-07-09 18:54:00','2025-07-11 18:54:00','vnr','IT',200,100,'A',1,'2025-07-09 18:54:00',1,'2025-07-09 18:54:00');
    
INSERT INTO eventCreation (
    event_id, name, start_date, end_date, venue, 
    event_organization, event_capacity, participant_count, 
    event_status, created_by, created_at, updated_by, updated_at
) 
VALUES 
(101, 'Tech Conference 2025', '2023-12-31 14:30:00', '2023-12-31 14:30:00', 'Grand Conference Hall', 
 'Tech Innovators Group', 500, 350, 'A', 1, '2023-12-31 14:30:00',  null,null);

INSERT INTO eventCreation (
    event_id, name, start_date, end_date, venue, 
    event_organization, event_capacity, participant_count, 
    event_status, created_by, created_at, updated_by, updated_at
) 
VALUES 
(1, 'Tech Conference 2025', '2025-03-15 09:00:00', '2025-03-15 17:00:00', 'Grand Conference Hall', 
 'Tech Innovators Group', 500, 350, 'A', 101, '2025-01-10 08:00:00', NULL, NULL);


INSERT INTO eventCreation (
    event_id, name, start_date, end_date, venue, 
    event_organization, event_capacity, participant_count, 
    event_status, created_by, created_at, updated_by, updated_at
) 
VALUES 
(1, 'Tech Conference 2025', now(), now(), 'Grand Conference Hall', 
 'Tech Innovators Group', 500, 350, 'A', 101, now(), NULL, NULL);

select * from eventCreation;



-- FeedBack
insert into feedback(user_id,event_id,rating,feedback)
values(1,201,5,"Good");

select * from feedback;

 
-- users 
INSERT INTO users (
    user_id, name, phn_number, email, role, 
    gender, status, dept
) 
VALUES 
(1, 'John Doe', '123-456-7890', 'john.doe@email.com', 'Admin', 'M', 'A', 'IT'),

(2, 'Jane Smith', '987-654-3210', 'jane.smith@email.com', 'Manager', 'F', 'A', 'HR');

select * from users;

-- event register
INSERT INTO eventRegistration (
    user_id, event_id, registration_status, 
    registered_by, registered_at, updated_by, updated_at
) 
VALUES 
(1, 101, 'R', 1, '2025-01-15 10:00:00', NULL, NULL);

select * from eventRegistration;