use sys;

create table Departments(
dept_id int unsigned primary key,
Manager_Id int unsigned unique,
dept_name varchar(20)
-- foreign key (Manager_Id) references Managers(Manager_Id)
);


create table Managers(
Emp_Id int unsigned,
Manager_Id int unsigned primary key, 
dept_id int unsigned unique,
Manager_Since Date,
foreign key (dept_id) references Departments(dept_id) on delete cascade,
-- foreign key (Emp_Id) references Employees(Emp_Id),
check(999<Emp_Id<10000)
);


create table Employees(
Emp_Id int unsigned primary key,
Employee_Name varchar(20) not null,
age int not null,
Date_Joined DateTime,
Manager_Id int unsigned not null,
dept_id int unsigned unique,
Foreign key (Manager_Id) references Managers(Manager_Id),
Foreign key (dept_id) references Departments(dept_id),
check(age>0),
check(999<Emp_Id<10000)
);


create table PayScale(
dept_id int unsigned unique,
designation varchar(20) not null,
`salary( in $)` decimal(10,2) default 0.00,
foreign key (dept_id) references Departments(dept_id),
primary key (dept_id,designation) 
);

insert into Departments(dept_id,Manager_Id,dept_name) values(1,1,'R&D');

alter table Departments add constraint foreign key (Manager_Id) references Managers(Manager_Id);
alter table Managers add constraint foreign key (Emp_Id) references Employees(Emp_Id);


desc Departments;


