
--1.
CREATE TABLE Employee_info_table 
(
    id int primary key,
    doj date,
    salary int
);

INSERT INTO Employee_info_table (id,doj,salary) VALUES ('12', '2021-06-06', '600000');
INSERT INTO Employee_info_table (id,doj,salary) VALUES ('23', '2020-01-01', '2000000');
INSERT INTO Employee_info_table (id,doj,salary) VALUES ('34', '2018-03-01', '4000000');

--2.
CREATE TABLE department_table 
(
    dept_id int primary key,
    dept_name varchar(300)
);

INSERT INTO department_table (dept_id,dept_name) VALUES ('1', 'cs');
INSERT INTO department_table (dept_id,dept_name) VALUES ('2', 'mech');
INSERT INTO department_table (dept_id,dept_name) VALUES ('3', 'ece');
INSERT INTO department_table (dept_id,dept_name) VALUES ('4', 'it');

--3.
CREATE TABLE insurance_table 
(
    insurance_id int primary key,
    insurance_name varchar(300)
);

INSERT INTO insurance_table (insurance_id,insurance_name) VALUES ('201', 'lic');
INSERT INTO insurance_table (insurance_id,insurance_name) VALUES ('203', 'sbi');
INSERT INTO insurance_table (insurance_id,insurance_name) VALUES ('344', 'medplus');
INSERT INTO insurance_table (insurance_id,insurance_name) VALUES ('342', 'bajaj');

--4.
CREATE TABLE office_table 
(
    office_id int primary key,
    office_name	varchar(300),
    place	varchar(300)
);

INSERT INTO office_table (office_id,office_name,place) VALUES ('501', 'medplus', 'hitech city');
INSERT INTO office_table (office_id,office_name,place) VALUES ('502', 'optival', 'kukatpally');
INSERT INTO office_table (office_id,office_name,place) VALUES ('503', 'custom furnish', 'gachibowli');
INSERT INTO office_table (office_id,office_name,place) VALUES ('504', 'diagnostics', 'ameerpet');





--5.

CREATE TABLE employee_table 
(
    emp_id int primary key,
    name varchar(300),
    immediate_head_id int,
    dept_id	int,
    insurance_id int,
    employee_info int,
    foreign key insurance_id reference insurance_table(insurance_id)
    foreign key employee_info reference Employee_info_table(id)
    foreign key dept_id reference department_table(dept_id)
);

INSERT INTO employee_table (emp_id,name,immediate_head_id,dept_id,insurance_id,employee_info) VALUES ('2201', 'harsha', '2001', '1', '201', '12');
INSERT INTO employee_table (emp_id,name,immediate_head_id,dept_id,insurance_id,employee_info) VALUES ('2202', 'shyam', '2002', '2', '202', '12');
INSERT INTO employee_table (emp_id,name,immediate_head_id,dept_id,insurance_id,employee_info) VALUES ('2203', 'dhansuh', '2003', '3', '344', '12');
INSERT INTO employee_table (emp_id,name,immediate_head_id,dept_id,insurance_id,employee_info) VALUES ('2204', 'srikar', '2004', '4', '342', '12');
INSERT INTO employee_table (emp_id,name,immediate_head_id,dept_id,insurance_id,employee_info) VALUES ('2001', 'vishwajeet', '1801', '1', '201', '23');
INSERT INTO employee_table (emp_id,name,immediate_head_id,dept_id,insurance_id,employee_info) VALUES ('2002','hari', '1801', '2', '203', '23');
INSERT INTO employee_table (emp_id,name,immediate_head_id,dept_id,insurance_id,employee_info) VALUES ('2003', 'vaibhav', '1801', '3', '344', '23');
INSERT INTO employee_table (emp_id,name,immediate_head_id,dept_id,insurance_id,employee_info) VALUES ('2004', 'mani teja', '1801', '4', '342', '23');
INSERT INTO employee_table (emp_id,name,immediate_head_id,dept_id,insurance_id,employee_info) VALUES ('1801', 'sharma', '', '1', '342', '34');


--6.

CREATE TABLE Employee_office_table 
(
    id	int primary key,
    emp_id	int,
    office_id int,
    foreign key emp_id reference employee_table(emp_id)
    foreign key office_id reference office_table (office_id)
);

INSERT INTO Employee_office_table (id,emp_id,office_id) VALUES ('201', '2201', '501');
INSERT INTO Employee_office_table (id,emp_id,office_id) VALUES ('202', '2202', '502');
INSERT INTO Employee_office_table (id,emp_id,office_id) VALUES ('203', '2203', '503');
INSERT INTO Employee_office_table (id,emp_id,office_id) VALUES ('204', '2204', '504');
INSERT INTO Employee_office_table (id,emp_id,office_id) VALUES ('205', '2001', '501');
INSERT INTO Employee_office_table (id,emp_id,office_id) VALUES ('206', '2002', '502');
INSERT INTO Employee_office_table (id,emp_id,office_id) VALUES ('207', '2003', '503');
INSERT INTO Employee_office_table (id,emp_id,office_id) VALUES ('208', '2004', '504');
INSERT INTO Employee_office_table (id,emp_id,office_id) VALUES ('209', '1801', '501');



------queries

--1.
select 
e.name,
d.dept_name,
i.insurance_name 
from employee_table as e 
join department_table as d on e.dept_id=d.dept_id 
join insurance_table as i on i.insurance_id = e.insurance_id ;


--2 . 
select 
name,
dept_name 
from employee_table e 
join employee_office_table eo on e.emp_id=eo.emp_id 
join office_table o on eo.office_id=o.office_id 
join department_table d on e.dept_id=d.dept_id 
where o.place="kukatpally";

--3.
select 
count(e.emp_id) as count,
place 
from employee_table e 
join  employee_office_table eo on e.emp_id=eo.emp_id 
join office_table o on eo.office_id=o.office_id 
group by place;

--4.
select 
name, 
insurance_name , 
salary from 
employee_table e 
join insurance_table i on e.insurance_id=i.insurance_id 
join employee_info_table ei on e.employee_info=ei.id 
order by ei.salary desc 
limit 1 
offset 2;


--5.
select 
avg(salary) as Average_Salary, 
place 
from employee_table e 
join employee_info_table ei on e.employee_info= ei.id 
join employee_office_table eo on eo.emp_id=e.emp_id 
join office_table o on eo.office_id=o.office_id 
where o.place="kukatpally";


--6.
select 
sum(ei.salary) as Sum_of_Salary,
place from employee_table e 
join employee_info_table ei on e.employee_info= ei.id 
join employee_office_table eo on eo.emp_id=e.emp_id 
join office_table o on eo.office_id=o.office_id 
where o.place="kukatpally" and ei.doj > "2020-09-08" ;

--7.
select
 e.name as name,
d.dept_name as Department_name,
salary as Salary


from employee_table e 
join employee_info_table ei on e.employee_info= ei.id 
join employee_office_table eo oneo.emp_id=e.emp_id 
join office_table o on eo.office_id=o.office_id 
join department_table d on e.dept_id=d.dept_id 
join insurance_table i on e.insurance_id=i.insurance_id

where 
ei.salary>200000 and place="gachibowli" and ei.doj>"2020-01-09" and 
i.insurance_name in ("lic","medplus");
;

--8.

select A.name as Employee_Name,
B.name as Head_Name
from employee_table as A , employee_table as B
where A.immediate_head_id=B.emp_id;

--9.
select 
A.name as Employee_Name,
d.dept_name as Department_Name, 
B.name as Head_Name,
d1.dept_name as Head_Department_Name

from employee_table as A , employee_table as B 
join department_table d on A.dept_id=d.dept_id 
join department_table d1 on B.dept_id=d1.dept_id
where A.immediate_head_id=B.emp_id;

--10 .
select 
i.name,
j.name as Head_name,
m.salary,
p.salary as Head_salary,
o.place as head_place

from 
Employee_table i 
join  Employee_table j on j.emp_id=i.immediate_head_id 
join Employee_info_table m on m.id=i.employee_info 
join Employee_info_table p on p.id=j.employee_info 
join Employee_office_table n on n.emp_id=j.emp_id 
join office_table o on o.office_id= n.office_id 
where o.place ="hitech city"  and p.salary > 2*m.salary;


