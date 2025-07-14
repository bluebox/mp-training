use sys;

-- SELECT CONSTRAINT_NAME
-- FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
-- WHERE TABLE_NAME = 'Departments' AND CONSTRAINT_TYPE = 'FOREIGN KEY';


create table Departments(
dept_id int unsigned primary key,
Manager_Id int unsigned,
dept_name varchar(20)
-- foreign key (Manager_Id) references Managers(Manager_Id)
);


create table Managers(
Emp_Id int unsigned,
Manager_Id int unsigned primary key, 
dept_id int unsigned unique,
Manager_Since Date,
foreign key (dept_id) references Departments(dept_id) on delete cascade,
check(Emp_Id>999 and Emp_Id<10000)
);


create table Employees(
Emp_Id int unsigned primary key,
Employee_Name varchar(20) not null,
age int not null,
Date_Joined DateTime,
Manager_Id int unsigned not null,
dept_id int unsigned,
Foreign key (Manager_Id) references Managers(Manager_Id),
Foreign key (dept_id) references Departments(dept_id),
check(age>0),
check(Emp_Id>999 and Emp_Id<10000)
);


create table PayScale(
dept_id int unsigned,
designation varchar(20) not null,
`salary( in $)` decimal(10,2) default 0.00,
foreign key (dept_id) references Departments(dept_id),
primary key (dept_id,designation) 
);




insert into Departments(dept_id,Manager_Id,dept_name) values(1,3,"R&D"),(2,1,"full_stack"),(3,5,"business"),(4,6,"QA"),(5,2,'testing'),(6,4,"back_end");
insert into Managers(Emp_Id, Manager_Id,dept_id,Manager_Since) values (2345,3,1,'2021-12-04'),(2456,1,2,'2022-09-03'),(2347,5,3,'2021-12-04'),(2356,6,4,'2022-09-03'),(2245,2,5,'2021-12-04'),(2496,4,6,'2022-09-03');
insert into Employees(Emp_Id, Employee_Name, age, Date_Joined, Manager_Id, dept_id) values
(2998,"Uday",23,'2022-06-22',3,1),(2598,"kanis",22,'2023-06-22',1,2),(2978,"Ram",23,'2021-06-22',5,3),(2498,"ravi",22,'2016-06-22',6,4),(2558,"Arun",22,'2019-06-22',2,5),
(2348,"Man",23,'2017-06-22',4,6);
insert into PayScale(dept_id,designation,`salary( in $)`) values (1,"associate",2300),(1,"senior associate",2500),(1,"junior associate",2700),
(2,"associate",2300),(2,"senior associate",2600),(2,"junior associate",2300),
(3,"associate",2200),(3,"senior associate",2700),(3,"junior associate",2400),
(4,"associate",2100),(4,"senior associate",2800),(4,"junior associate",2500),
(5,"associate",2700),(5,"senior associate",2900),(5,"junior associate",2600),
(6,"associate",2400),(6,"senior associate",3000),(6,"junior associate",2900);


alter table Departments add constraint foreign key (Manager_Id) references Managers(Manager_Id);


select * from Managers;
select * from Departments;
select * from Employees;
select * from PayScale;

select Manager_Id,dept_id from Managers;

select Employee_Name from Employees order by Date_Joined;

select Employee_Name from Employees order by Date_Joined limit 2;

select * from PayScale where designation like "%junior%";

update PayScale set `salary( in $)`=3500 where designation="senior associate";

delete from PayScale where dept_id=3;

select * from PayScale; 

select Ep.Emp_Id, Ep.dept_id,mg.Manager_Since from Employees as Ep left join Managers as mg on Ep.Emp_Id=mg.Emp_Id;

select Ep.Emp_Id, Ep.dept_id,mg.Manager_Since from Employees as Ep right join Managers as mg on Ep.Emp_Id=mg.Emp_Id; 


select Ep.Emp_Id, Ep.dept_id,mg.Manager_Since from Employees as Ep left join Managers as mg on Ep.Emp_Id=mg.Emp_Id
union 
select Ep.Emp_Id, Ep.dept_id,mg.Manager_Since from Employees as Ep right join Managers as mg on Ep.Emp_Id=mg.Emp_Id; 

select age,count(*) from Employees group by age;

select designation,sum(`salary( in $)`) from PayScale group by designation;

select designation,avg(`salary( in $)`) from PayScale group by designation;

select designation,max(`salary( in $)`) from PayScale group by designation;

select designation,min(`salary( in $)`) from PayScale group by designation;

select designation from PayScale group by designation having avg(`salary( in $)`)>3000;

select designation from PayScale;

desc Departments;

explain select * from  Employees where Manager_Id = 3;

create index indexing_manager_id on Employees(Manager_Id);

explain select * from Employees where Manager_Id=3;

insert into Employees(Emp_Id,Employee_Name,age,Date_Joined,Manager_Id,dept_id) values(978,"bhanu",22,'2023-09-03',3,1);

insert into Employees(Emp_Id,Employee_Name,age,Date_Joined,Manager_Id,dept_id) values(9768,"bhanu",-1,'2023-09-03',3,1);

