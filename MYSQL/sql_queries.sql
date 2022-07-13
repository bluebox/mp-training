create database db1;
use db1;

	
    create table Department(dept_id varchar(50) primary key,  dept_name varchar(50));

	insert into Department values('10','Accounts');
	insert into Department values('20','HR');
	insert into Department values('30','Sales');
	insert into Department values('40','Software');

	Create table Insurance(insurance_id varchar(50) primary,
	insurance_name varchar(50) not null)

	Insert into insurance values('LIC01', 'LIC');
	Insert into insurance values('ICICI01', 'ICICI');
	Insert into insurance values('HDFC01', 'HDFC');
	Insert into insurance values('LIC02', 'LIC');

	CREATE TABLE EMPLOYEE_INFO( id varchar(50) primary key, 
                           	doj date not null, salary float not null)
		
	insert into employee_info values('ME023', '06/06/2022','21000.0');
	insert into employee_info values('ME024', '06/06/2022','30000.0');
	insert into employee_info values('ME001', '21/07/2018','75000.42');
	insert into employee_info values('ME055', '23/11/2019','40000.23');

 	create table Office(office_id varchar(50) primary key,
                    office_name varchar(50) not null,
                    place varchar(50) not null)

	insert into Office values('HYD05', 'Medplus','Madhapur');
	insert into Office values('HYD04', 'Optival','Moosepet');
	insert into Office values('HYD03', 'MEIL','Balanage');
	insert into Office values('HYD06', 'Milestone','Madhapur');

 	create table Employee(emp_id varchar(50) primary key,
                      name varchar(50) not null,
                      immediate_head_id varchar(50),
                      dept_id varchar(50),
                      insurance_id varchar(50),
   foreign key (dept_id) references Department(dept_id),
   foreign key (insurance_id) references insurance(insurance_id));   

insert into employee values('ME023','Dhanush','ME055','40','LIC01'); 
insert into employee values('ME024','Gowtham','ME055','40','HDFC01'); 
insert into employee values('ME055','Mani Teja','ME001','40','ICICI01');
insert into employee values('ME001','Sharma','','40','LIC02'); 
   

CREATE TABLE Employee_office(id varchar(50) not null,
                             emp_id varchar(50),
                             office_id varchar(50),
   foreign key(emp_id) references Employee(emp_id),
   foreign key(office_id) references Office(office_id));
INSERT INTO EMPLOYEE_OFFICE VALUES('01','ME001','HYD05');
INSERT INTO EMPLOYEE_OFFICE VALUES('02','ME055','HYD05');
INSERT INTO EMPLOYEE_OFFICE VALUES('03','ME023','HYD05');
INSERT INTO EMPLOYEE_OFFICE VALUES('04','ME024','HYD04');
 ---------------------------
 --1 query answer
  select  name ,d.dept_name ,isurance_name from  Department_Table d inner join Employee_Table e on d.dept_id=e.dept_id inner join insurance_Table as i
on e.insurance_id=i.insurance_id;


--2 query answer
select d.dept_name from Department_Table d inner join Employee_Table e on
d.dept_id=e.dept_id inner join Employee_Office_Table eo on e.emp_id=eo.emp_id
 nner join Office_Table of on eo.office_id=of.office_id where –of.place="hiteccity";

--3 query answer
select place ,count(emp_id) from Office_Table ot
 inner join Employee_Office_Table ef on ot.office_id=ef.office_id
 group by ef.emp_id;

--4 query answer
select name,isurance_name,salary from Insurance_Table it inner join Employee_Table et on it.insurance_id=et.insurance_id inner join Employee_Office_Table eot on et.emp_id=eot.emp_id inner join Employee_Info_Table eit on eot.id=eit.id  order by salary desc limit 2,1;


--5 query answer
select dept_id,avg(salary) from Employee_Info_Table eit inner join Employee_Office_Table eot on eit.id=eot.id inner join Employee_Table et
 on eot.emp_id=et.emp_id inner join Office_Table ot on eot.office_id=ot.office_id where ot.place="hiteccity" group by dept_id;

--6 query answer
select sum(salary) from Employee_Info_Table as eit inner join Employee_Office_Table eot on eit.id=eot.id inner join Office_Table ot
on eot.office_id=ot.office_id  where ot.place ="hiteccity" and  eit.DOJ>"2022-01-02";

--seventh query answer

select name,dept_name,salary from employee_table et inner join department_table dt on dt.dept_id=et.dept_id inner join employee_office_table eot on eot.emp_id=et.emp_id inner join employee_info_table eit on eit.id=eot.id inner join insurance_table it on it.insurance_id=et.insurance_id inner join office_table ot on ot.office_id=eot.office_id where doj>'02-02-2022' and salary>20000 and it.insurance_name in ('LIC','ICICI') and ot.place='madhapur' ;


--8 query

select name,(select name from employee_table where emp_id=emp.immediate_head_id) as immediate_head_name from Employee_Table emp

select i.name as name,e.name as immediate_head_name from employee_table e ,employee_table i where e.emp_id=i.immediate_head_id;

--nineth query anser

select name,dt.dept_name ,(select name from employee_table where emp_id=emp.immediate_head_id) as immediate_head_name, (select di.dept_name from employee_table empp
inner join department_table di on empp.dept_id = di.dept_id
where emp_id = emp.immediate_head_id) as immediate_head_department from employee_table emp
inner join department_table dt on emp.dept_id = dt.dept_id;

--tenth query answer

select emp.name from employee_table emp 
inner join employee_info_table EOT on emp_info = EOT.id
where EOT.salary < (select EOT.salary from employee_table 
 inner join employee_info_table EOT on emp_info = EOT.id
 where emp_id = emp.immediate_head_id
and emp.emp_id in (select emp_id from employee_table aemp
 where aemp.emp_id in (
 select EOT.id from employee_off_table EOT
inner join office_table OT on EOT.off_id = OT.off_id where OT.place = "madhapur")))


                                                                  








                                                                                       
                                                                                          


                                                                                             
                                                                                       
                                                              
 
 




 



