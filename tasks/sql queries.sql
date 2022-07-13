CREATING TABLES

---Department_Table (dept_id(PK), dept_name)
create table Department_Table (dept_id int primary key , dept_name varchar(50));

-- inserting values into department table
INSERT INTO Department_Table VALUES (001,'Development');
INSERT INTO Department_Table VALUES (002,'Testing');
INSERT INTO Department_Table VALUES (003,'Design');
INSERT INTO Department_Table VALUES (004,'HR');
select * from Department_Table;


--- Insurance_Table (insurance_id(PK), isurance_name)
create table Insurance_Table (insurance_id int primary key , insurance_name varchar(100));
-- inserting values into Insurance_Table
INSERT INTO Insurance_Table VALUES (201,'insurance 1');
INSERT INTO Insurance_Table VALUES (202,'insurance 2');
INSERT INTO Insurance_Table VALUES (203,'insurance 3');
INSERT INTO Insurance_Table VALUES (204,'insurance 4');
select * from Insurance_Table;

--- Office_Table(office_id(PK), office_name, place)
create table Office_Table (office_id int primary key , office_name varchar(50) , place varchar(50) );
---inserting values into Office_Table
INSERT INTO Office_Table VALUES (301,'medplus software','Madhapur');
INSERT INTO Office_Table VALUES (302,'medplus warehouse','Gachibouli');
INSERT INTO Office_Table VALUES (303,'medplus main branch','Madhapur');
select * from Office_Table;

--- Employee_Info_Table(id(PK), DOJ, salary)

create table Employee_Info_Table (employee_info_id int primary key , DOJ Date , salary decimal(15,3) );
---inserting values into Employee_Info_Table
INSERT INTO Employee_Info_Table VALUES (401,'2022-06-06',600000.00);
INSERT INTO Employee_Info_Table VALUES (402,'2022-06-06',600000.00);
INSERT INTO Employee_Info_Table VALUES (403,'2019-08-01',800000.50);
INSERT INTO Employee_Info_Table VALUES (404,'2018-06-01',1000000.50);
select * from Employee_Info_Table;



---i. Employee_Table( emp_id(PK), name, immediate_head_id,dept_id(FK), insurance_id(FK),   employee_info(FK))

create table Employee_Table (emp_id varchar(10) primary key , immediate_head_id , dept_id references Department_Table(dept_id), insurance_id references Insurance_Table(insurance_id), employee_info_id references Employee_Info_Table (employee_info_id));

alter table employee_table add  immediate_head_id foriegn key references employee_table(emp_id);

----INSERTING IMMEDIATE HEAD VALUES
update employee_table set immediate_head_id = 'MP501' WHERE EMP_ID = 'MP507';


---inserting values into Employee_Table
INSERT INTO Employee_Table VALUES ('MP501','MP503', 101, 201, 402);
INSERT INTO Employee_Table VALUES ('MP502','MP503', 103, 202, 403);
INSERT INTO Employee_Table VALUES ('MP503','MP504', 102, 203, 403);
INSERT INTO Employee_Table VALUES ('MP504','MP504', 102, 202, 401);
INSERT INTO Employee_Table VALUES ('MP507','MP505', 102, 202, 401);
select * from Employee_Table;


---ADDING NAME FIELD
ALTER TABLE EMPLOYEE_TABLE ADD NAME VARCHAR(100);
--- ADDING NAMES
UPDATE  EMPLOYEE_TABLE SET NAME = "ANITHA" WHERE EMP_ID = 'MP506';
select * from Employee_Table;

---- Employee_Office_Table(id(PK), emp_id(FK), office_id(FK)

-REATE TABLE EMPLOYEE_OFFICE_TABLE ( EMP_OFC_ID VARCHAR(10) PRIMARY KEY , EMP_ID REFERENCES EMPLOYEE_TABLE (EMP_ID) , OFFICE_ID REFERENCES OFFICE_TABLE(OFFICE_ID) );

---INSERTING VALUES TO EMPLOYEE_OFFICE_TABLE

INSERT INTO EMPLOYEE_OFFICE_TABLE VALUES ( 'MED607', 'MP505' , 301 );
select * from EMPLOYEE_OFFICE_TABLE;





----------------------------------------------QUERIES--------------------------------------------------
----1. Get the list of employee details which should include their name,department and name of the insurance type 


SELECT EMPLOYEE_TABLE.NAME,EMPLOYEE_TABLE.EMP_ID , DEPARTMENT_TABLE.DEPT_NAME , INSURANCE_TABLE.INSURANCE_NAME 
FROM EMPLOYEE_TABLE,DEPARTMENT_TABLE,INSURANCE_TABLE
WHERE EMPLOYEE_TABLE.DEPT_ID = DEPARTMENT_TABLE.DEPT_ID AND 
EMPLOYEE_TABLE.INSURANCE_ID = INSURANCE_TABLE.INSURANCE_ID;


----2.Get the list of departments of employees working at particular place(e.g: Madhapur)

SELECT EMPLOYEE_TABLE.EMP_ID, DEPARTMENT_TABLE.DEPT_NAME FROM DEPARTMENT_TABLE, EMPLOYEE_TABLE
WHERE  EMPLOYEE_TABLE.DEPT_ID = DEPARTMENT_TABLE.DEPT_ID AND EMPLOYEE_TABLE.EMP_ID IN
(
SELECT DISTINCT EMPLOYEE_OFFICE_TABLE.EMP_ID  FROM EMPLOYEE_OFFICE_TABLE,OFFICE_TABLE,EMPLOYEE_TABLE
WHERE EMPLOYEE_OFFICE_TABLE.OFFICE_ID = OFFICE_TABLE.OFFICE_ID AND OFFICE_TABLE.PLACE = 'Madhapur');


----3.Get the count of employees working in each location

SELECT *  FROM EMPLOYEE_OFFICE_TABLE INNER JOIN OFFICE_TABLE ON EMPLOYEE_OFFICE_TABLE.OFFICE_ID = OFFICE_TABLE.OFFICE_ID ;


---4.Get the third highest salaried employee name, insurance name and salary(Joins)

SELECT EMPLOYEE_TABLE.NAME , INSURANCE_TABLE.INSURANCE_NAME , Employee_Info_Table.SALARY
FROM EMPLOYEE_TABLE INNER 
JOIN INSURANCE_TABLE ON EMPLOYEE_TABLE.INSURANCE_ID = INSURANCE_TABLE.INSURANCE_ID
INNER JOIN Employee_Info_Table ON EMPLOYEE_TABLE.EMPLOYEE_INFO_ID = EMPLOYEE_INFO_TABLE.EMPLOYEE_INFO_ID 
ORDER BY Employee_Info_Table.SALARY DESC  LIMIT 1 OFFSET 2 ;


----5.Get the Average salary of employees from each department for a particular location

SELECT avg(salary), dept_name, place 
from Employee_Info_Table 
INNER JOIN Employee_Table on employee_info_table.employee_info_id =	 EMPLOYEE_TABLE.employee_info_id 
INNER JOIN employee_office_table ON EMPLOYEE_TABLE.EMP_ID = EMPLOYEE_OFFICE_TABLE.emp_id 
inner join office_table on EMPLOYEE_OFFICE_TABLE.office_id = office_table.office_id
inner join department_table on EMPLOYEE_TABLE.dept_id = department_table.dept_id 
group by office_table.place , department_table.dept_name;



---6.Get the sum of salaries of the employees joined after 02-01-2022 working in ‘Madhapur’


select sum(salary) from employee_info_table 
inner join employee_table on employee_table.employee_info_id = employee_info_table.employee_info_id 
inner join employee_office_table on employee_table.emp_id = employee_office_table.emp_id 
inner join office_table on employee_office_table.office_id = office_table.office_id
where Employee_Info_Table.DOJ > '2022-01-02' and office_table.place = "Madhapur";



---7.Get name, department, salary of employees who joined after 20-02-2022 and  salary is greater than 20000 and has insurance either in lic or icici or both and whose work location is madhapur.


select distinct  employee_table.emp_id,employee_table.name , department_table.dept_name , Employee_Info_Table.salary
from EMPLOYEE_TABLE 
INNER JOIN EMPLOYEE_OFFICE_TABLE ON EMPLOYEE_TABLE.EMP_ID = EMPLOYEE_OFFICE_TABLE.EMP_ID 
INNER JOIN OFFICE_TABLE ON EMPLOYEE_OFFICE_TABLE.OFFICE_ID = OFFICE_TABLE.OFFICE_ID
INNER JOIN DEPARTMENT_TABLE ON DEPARTMENT_TABLE.DEPT_ID = EMPLOYEE_TABLE.DEPT_ID
INNER JOIN INSURANCE_TABLE ON EMPLOYEE_TABLE.INSURANCE_ID = INSURANCE_TABLE.INSURANCE_ID 
INNER JOIN EMPLOYEE_INFO_TABLE ON EMPLOYEE_TABLE.EMPLOYEE_INFO_ID = EMPLOYEE_INFO_TABLE.employee_info_id WHERE Employee_Info_Table.DOJ > "2022-02-20" AND Employee_Info_Table.SALARY > 20000 AND INSURANCE_TABLE.INSURANCE_NAME IN ('insurance 1','insurance 2') ;



---8. Get the list of employee names and their respective immediate head name


select a.name "Employee" ,b.name "Immediate Head"
from Employee_Table a 
join Employee_Table b 
on a.immediate_head_id = b.emp_id;


---9. Get the list of employee names and their respective immediate head name with  their respective department names.
----  Eg: emp name | emp department name | immediate head name | immediate head department name

select e1.name "Emp Name", d1.dept_name "Emp Dept Name", e2.name "Head Name", d2.dept_name "Head Dept Name"
from Employee_Table e1 join Employee_Table e2
on e1.immediate_head_id = e2.emp_id
join Department_Table d1 on d1.dept_id = e1.dept_id
join Department_Table d2 on d2.dept_id = e2.dept_id;



----10. Get the list of employees whose immediate head working in location ‘madhapur’and whose salary is less than twice the salary of their respective immediate   heads.


select e1.name "Emp"
from Employee_Table e1 join Employee_Table e2
on e1.immediate_head_id = e2.emp_id
join Employee_Office_Table eo on e2.emp_id = eo.emp_id
join Office_Table o on eo.office_id = o.office_id
join Employee_info_Table ei1 on e1.employee_info_id = ei1.employee_info_id
join Employee_info_Table ei2 on e2.employee_info_id = ei2.employee_info_id
where o.place = "Madhapur" and 2*ei1.Salary < (ei2.Salary);
