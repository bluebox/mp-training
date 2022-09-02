create database railwayManagment;
use railwayManagment;
create table User (
user_id int,
first_name varchar(50),
last_name varchar(50),
adhar_no varchar(50),
gender char,
age int,
mobile_no varchar(50),
email varchar(50),
city varchar(50),
state varchar(50),
pincode varchar(20),
password varchar(50),
security_ques varchar(50),
security_ans varchar(50)
);

ALTER TABLE `railwayManagment`.`User` 
CHANGE COLUMN `user_id` `user_id` INT NOT NULL ,
ADD PRIMARY KEY (`user_id`);
;

create table Train (
train_no int,
train_name varchar(50),
arrival_time time,
departure_time time,
availability_of_seats char,
train_date date
);
ALTER TABLE `railwayManagment`.`Train` 
ADD PRIMARY KEY (`train_no`);
;

create table Station (
station_no int primary key,
station_name varchar(50),
haut int,
arrival_time time,
train_no int,
CONSTRAINT train_no_fk foreign key(train_no) references Train(train_no)
);

create table TrainStatus (
train_no int primary key,
b_seats1 int,
b_seats2 int,
a_seats1 int,
a_seats2 int,
w_seats1 int,
w_seats2 int,
fare1 float,
fare2 float
);

create table Ticket (
id int primary key,
user_id int,
status char,
no_of_passengers int,
train_no int,
constraint ticket_fk foreign key(user_id) references User(user_id)
);

ALTER TABLE `railwayManagment`.`Ticket` 
add constraint foreign key(train_no) references Train(train_no)
;

create table Passenger(
passenger_id int primary key,
pnr_no int,
age int,
gender char,
user_id int,
reservation_status char,
seat_number varchar(5),
name varchar(50),
ticket_id int,
constraint foreign key(user_id) references User(user_id),
constraint foreign key(ticket_id) references Ticket(id)
);

create table Started (
train_no int primary key,
station_no int,
constraint foreign key(train_no) references Train(train_no),
constraint foreign key(station_no) references Station(station_no)
);

create table Stop_at (
train_no int primary key,
station_no int,
constraint foreign key(train_no) references Train(train_no),
constraint foreign key(station_no) references Station(station_no)
);

create table Reaches (
train_no int,
station_no int,
timinig time,
constraint foreign key(train_no) references Train(train_no),
constraint foreign key(station_no) references Station(station_no)
);

create table Books(
user_id int,
id int,
constraint foreign key(user_id) references User(user_id),
constraint foreign key(id) references Ticket(id)
);

create table Cancel(
user_id int,
id int,
passenger_id int,
constraint foreign key(user_id) references User(user_id),
constraint foreign key(id) references Ticket(id),
constraint foreign key(passenger_id) references Passenger(passenger_id)
);

drop table Ticket;




