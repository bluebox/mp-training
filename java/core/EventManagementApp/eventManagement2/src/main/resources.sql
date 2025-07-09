create database EventManagement;
show databases;
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
created_at datetime not null,
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


desc eventCreation;
select * from eventCreation;

ALTER TABLE eventCreation
ADD COLUMN created_at datetime null
AFTER created_by; 

ALTER TABLE eventCreation
DROP COLUMN updated_by; 

ALTER TABLE eventCreation
DROP COLUMN updated_at; 


