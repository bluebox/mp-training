show schemas;

create database Training;

use Training;

create table emp(
id varchar(20) primary key,
name varchar(40),
phn int,
location varchar(40),
email varchar(50) unique
);

insert into emp values('1','Purnima',12345,'kolkata','puri@gmail.com');
insert into emp values('2','Yatin',13445,'Delhi','yatin@gmail.com');
insert into emp values('3','Sakshi',98745,'Lucknow','sakshi@gmail.com');
insert into emp values('4','Irfan',36745,'Bangalore','irfan@gmail.com');
insert into emp values('5','Nikita',86549,'Hyderabad','nikita@gmail.com');

select name from emp where location = 'Delhi';

select * from emp;

create table emp_salary(
id varchar(20) primary key,

salary int,
CONSTRAINT fk_id FOREIGN KEY (id) REFERENCES emp(id)  
);

insert into emp_salary values('1',250000);
insert into emp_salary values('2',210000);
insert into emp_salary values('3',260000);
insert into emp_salary values('4',280000);
insert into emp_salary values('5',220000);

select * from emp_salary;

select t1.name, t2.salary from emp t1 join emp_salary t2 on t1.id=t2.id;

update emp set name='Purnima Agarwal' where id='1';

alter table emp_salary add dob date;

select * from emp where location like '%o%';

CREATE USER 'purnima'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Purnima1!';
GRANT ALL PRIVILEGES ON Training.* TO 'purnima'@'localhost' with grant option;

select id, salary from emp_salary order by salary desc limit 1;

select avg(salary) from emp_salary;

select min(salary) from emp_salary;

select max(salary) from emp_salary;


