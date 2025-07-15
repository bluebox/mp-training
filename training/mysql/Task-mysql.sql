use sys;

-- SELECT CONSTRAINT_NAME
-- FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
-- WHERE TABLE_NAME = 'Departments' AND CONSTRAINT_TYPE = 'FOREIGN KEY';


create table Departments(
dept_id int unsigned primary key,
dept_name varchar(20) not null,
check(dept_id>=1)
);


create table Employees(
Emp_Id int unsigned primary key,
Employee_Name varchar(20) not null,
dob date not null,
Date_Joined date default (current_date),
dept_id int unsigned,
Foreign key (dept_id) references Departments(dept_id) on delete cascade,
check(Emp_Id>999 and Emp_Id<10000),
check(timestampdiff(year,dob,Date_Joined)>=18)
);

create table DepartmentHeads(
Emp_Id int unsigned,
dept_head_Id int unsigned primary key, 
dept_id int unsigned unique,
dept_head_since Date,
foreign key(Emp_Id) references Employees(Emp_Id) on delete cascade,
foreign key (dept_id) references Departments(dept_id) on delete cascade,
check(Emp_Id>999 and Emp_Id<10000)
);

create table Designations(
designation varchar(20) primary key
);

create table PayScale(
dept_id int unsigned,
designation varchar(20),
`salary( in $)` decimal(10,2) default 0.00,
foreign key (dept_id) references Departments(dept_id) on delete cascade,
foreign key (designation) references Designations(designation) on delete cascade,
primary key (dept_id,designation),
check(`salary( in $)`>80000 and `salary( in $)`<100000) 
);




insert into Departments(dept_id,dept_name) values(1,"R&D"),(2,"full_stack"),(3,"business"),(4,"QA"),(5,'testing'),(6,"back_end");

insert into Employees(Emp_Id, Employee_Name, dob, Date_Joined, dept_id) values
(2998,"Uday",'2000-06-04','2022-06-22',1),(2598,"kanis",'2000-06-04','2023-06-22',2),(2978,"Ram",'2000-06-04','2021-06-22',3),(2498,"ravi",'2000-06-04','2021-06-22',3),(2558,"Arun",'2000-06-04','2019-06-22',5),
(2748,"Man",'2000-06-04','2019-06-22',5),(2948,"Man",'2000-06-04','2019-06-22',4),(2848,"Man",'2000-06-04','2019-06-22',6);

insert into DepartmentHeads(Emp_Id,dept_head_id,dept_id,dept_head_since) values (2998,1,1,'2021-12-04'),(2598,2,2,'2021-12-04'),(2498,3,3,'2022-09-03'),(2748,4,4,'2021-12-04'),(2948,5,5,'2022-09-03'),(2848,6,6,'2021-12-04');


insert into Designations(designation) values ("associate"),("senior associate"),("junior associate");

insert into PayScale(dept_id,designation,`salary( in $)`) values (1,"associate",83000),(1,"senior associate",85000),(1,"junior associate",87000),
(2,"associate",83000),(2,"senior associate",86000),(2,"junior associate",83000),
(3,"associate",82000),(3,"senior associate",87000),(3,"junior associate",84000),
(4,"associate",81000),(4,"senior associate",88000),(4,"junior associate",85000),
(5,"associate",87000),(5,"senior associate",89000),(5,"junior associate",86000),
(6,"associate",81000),(6,"senior associate",82000),(6,"junior associate",89000);



select * from DepartmentHeads;
select * from Departments;
select * from Employees;
select * from Designations;
select * from PayScale;

select dept_head_Id,dept_id from DepartmentHeads;

select Employee_Name from Employees order by Date_Joined;

select Employee_Name from Employees order by Date_Joined limit 2;

select * from PayScale where designation like "%junior%";

update PayScale set `salary( in $)`=85000 where designation="senior associate";

delete from PayScale where dept_id=3;

select * from PayScale; 

-- all the employees department details
select Ep.Emp_Id, Ep.dept_id,dp.dept_name from Employees as Ep left join Departments as dp on Ep.dept_id=dp.dept_id;

#info about all the department heads
select Ep.Emp_Id, Ep.dept_id,dh.dept_head_Since from Employees as Ep right join DepartmentHeads as dh on Ep.Emp_Id=dh.Emp_Id; 


select Ep.Emp_Id, Ep.dept_id,dh.dept_head_since from Employees as Ep left join DepartmentHeads as dh on Ep.Emp_Id=dh.Emp_Id
union 
select Ep.Emp_Id, Ep.dept_id,dh.dept_head_since from Employees as Ep right join DepartmentHeads as dh on Ep.Emp_Id=dh.Emp_Id; 

-- select age,count(*) from Employees group by age;

select designation,sum(`salary( in $)`) from PayScale group by designation;

select designation,avg(`salary( in $)`) from PayScale group by designation;

select designation,max(`salary( in $)`) from PayScale group by designation;

select designation,min(`salary( in $)`) from PayScale group by designation;

select designation from PayScale group by designation having avg(`salary( in $)`)>85000;

select designation from PayScale;

desc Departments;


-- testing foreign key
explain select * from  Employees where dept_id = 3;

create index indexing_dept_id on Employees(dept_id);

explain select * from Employees where dept_id=3;

-- indexing testing 

explain select * from  Employees where Date_Joined = '2021-06-22';

create index indexing_date_joined on Employees(Date_Joined);

explain select * from Employees where Date_Joined='2021-06-22';

insert into Employees(Emp_Id,Employee_Name,dob,Date_Joined,dept_id) values(3748,"bhanu",'2024-09-03','2023-09-03',1);

insert into Employees(Emp_Id,Employee_Name,dob,Date_Joined,dept_id) values(976,"bhanup",'2000-08-03','2023-09-03',1);

