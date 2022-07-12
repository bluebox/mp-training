--creation of Depatment table
CREATE TABLE Department_Table (
Dept_ID INT PRIMARY KEY,
Dept_Name varchar(30) NOT NULL
);

--creation of Insurance table
CREATE TABLE Insurance_Table(
Insurance_id INT PRIMARY KEY,
Insurance_name varchar(30) NOT NULL);
 
 
--creation of office table
CREATE TABLE Office_Table(
Office_id INT PRIMARY KEY,
Office_name varchar(30) NOT NULL,
Place varchar(30) NOT NULL); 


--creation of employee info table
CREATE TABLE Employee_Info_Table(
Id INT PRIMARY KEY,
DOJ  date  NOT NULL,
SALARY INT NOT NULL);


--creation of employee

CREATE TABLE EMPLOYEE (
  Emp_id INT PRIMARY KEY NOT NULL,
  Emp_Name varchar(20) NOT NULL,
  Immediate_head_id INT ,
  Dept_ID int NOT NULL,
  Insurance_id INT NOT NULL,
  Id INT NOT NULL,
  foreign key(Dept_ID) references Department_Table(Dept_ID) ,
  foreign key(Insurance_id) references Insurance_Table(Insurance_id) ,
  foreign key(Id) references Employee_Info_Table(Id) 
);


--creation of emplyee office table
CREATE TABLE Employee_Office_Table(
id INT PRIMARY KEY,
Emp_id  INT NOT NULL,
Office_id INT NOT NULL,
foreign key (Emp_id) references EMPLOYEE(Emp_id));


--creation of employee office table
CREATE TABLE Employee_Office_Table(
id INT PRIMARY KEY,
Emp_id  INT NOT NULL,
Office_id INT NOT NULL,
foreign key (Emp_id) references EMPLOYEE(Emp_id),
FOREIGN KEY (Office_id) REFERENCES Office_Table(Office_id));

--INSERTIND DEPARTMENT RECORDS
INSERT INTO Department_Table VALUES(501, 'Associate Software Engineer');
INSERT INTO Department_Table VALUES(502, 'Embedded Hardware');
INSERT INTO Department_Table VALUES(503, 'Artificial Intelligence');
INSERT INTO Department_Table VALUES(504, 'Data Scientist');
INSERT INTO Department_Table VALUES(505, 'Java Full Stack Developer');
INSERT INTO Department_Table VALUES(506, 'Python Full stack developer');
INSERT INTO Department_Table VALUES(507, 'C++ Developer');
INSERT INTO Department_Table VALUES(508, 'Front End develoiper');


--INSURANCE TABLE
INSERT INTO Insurance_Table VALUES(301, 'Health insurance');
INSERT INTO Insurance_Table VALUES(302, 'Life Insurance');
INSERT INTO Insurance_Table VALUES(303, 'Vehicle Insurance');
INSERT INTO Insurance_Table VALUES(304, 'Electrical Appliance insurance');


--OFFICE TABLE
INSERT INTO Office_Table VALUES(601, " Medplus", "Hyderabad");
INSERT INTO Office_Table VALUES(602, "Medplus", "Bangalore");
INSERT INTO Office_Table VALUES(603, "Medplus", "Mumbai");
INSERT INTO Office_Table VALUES(604, "Medplus", "Delhi");
INSERT INTO Office_Table VALUES(605, "Medplus", "Kolkatta");

--EMPLOYE INFORMATION TABLE
INSERT INTO Employee_Info_Table VALUES(705, '2020-04-06', 8000);
INSERT INTO Employee_Info_Table VALUES(703, '2021-09-07', 9000);
INSERT INTO Employee_Info_Table VALUES(704, '2022-08-14', 4000);

-- EMPLOYEE
INSERT INTO EMPLOYEE VALUES (0001, 'Ramesh', '13','501', '301','701');
INSERT INTO EMPLOYEE VALUES (0002, 'Mahesh', '14','502', '302','702');
INSERT INTO EMPLOYEE VALUES (0003, 'suresh', '15','503', '302','702');
INSERT INTO EMPLOYEE VALUES (0004, 'Rajesh', '15','504', '303','703');
INSERT INTO EMPLOYEE VALUES (0005, 'Ram', '16','505', '304','705');
INSERT INTO EMPLOYEE VALUES (0006, 'Vishnu', '16','506', '302','706');
INSERT INTO EMPLOYEE VALUES (0007, 'Shyam', '13','507', '301','707');
INSERT INTO EMPLOYEE VALUES (0008, 'shashi', '17','508', '304','701');
INSERT INTO EMPLOYEE VALUES (0009, 'Navya', '12','501', '304','702');
INSERT INTO EMPLOYEE VALUES (0010, 'Shirisha', '13','502', '301','701');
INSERT INTO EMPLOYEE VALUES (0011, 'Janu', '12','503', '301','703');
INSERT INTO EMPLOYEE VALUES (0012, 'Vani', ,'504', '302','703');
INSERT INTO EMPLOYEE VALUES (0013, 'Laxshmi', ,'505', '302','704');
INSERT INTO EMPLOYEE VALUES (0014, 'Bargavi', '126','506', '301','705');
INSERT INTO EMPLOYEE VALUES (0015, 'Bindu', ,'507', '304','706');
INSERT INTO EMPLOYEE VALUES (0016, 'Varsha', ,'508', '302','707');
INSERT INTO EMPLOYEE VALUES (0017, 'Nashitha', ,'505', '301','708');


--EMPLOYEE OFFICE TABLE
iNSERT INTO Employee_Office_Table VALUES(801, 001, 601);
INSERT INTO Employee_Office_Table VALUES(802, 002, 602);
INSERT INTO Employee_Office_Table VALUES(803, 003, 603);
INSERT INTO Employee_Office_Table VALUES(804, 004, 604);
INSERT INTO Employee_Office_Table VALUES(805, 005, 605);
INSERT INTO Employee_Office_Table VALUES(806, 006, 601);
INSERT INTO Employee_Office_Table VALUES(807, 007, 602);
INSERT INTO Employee_Office_Table VALUES(808, 008, 603);
INSERT INTO Employee_Office_Table VALUES(809, 009, 604);
INSERT INTO Employee_Office_Table VALUES(810, 010, 605);


-- Write the queries to,
 
-- 1. Get the list of employee details which should include their name,department and 
--     name of the insurance type 


-- SQL>>
--   SELECT EMPLOYEE.emp_name,Department_Table.Dept_Name, Insurance_Table.insurance_name
-- FROM   EMPLOYEE, Department_Table,Insurance_Table
-- WHERE  EMPLOYEE.dept_id = Department_Table.dept_id AND EMPLOYEE.insurance_id=Insurance_Table.insurance_id


-- 2.Get the list of departments of employees working at particular place(e.g:  Madhapur)
-- sql>>
-- SELECT Department_Table.Dept_Name
-- FROM   EMPLOYEE, Department_Table, Employee_Office_Table,Office_Table
-- where Office_Table.Office_Id = Employee_Office_Table.Office_Id And Employee_Office_Table.Emp_id = EMPLOYEE.Emp_id And EMPLOYEE.Dept_ID = Department_Table.Dept_ID And Office_Table.Place = "Madhapur";


-- 3.Get the count of employees working in each location
-- SQL>>
-- SELECT Place, COUNT(*) AS total_count from (SELECT Office_Table.place,EMPLOYEE.Emp_Name
-- FROM EMPLOYEE, Employee_Office_Table,Office_Table
-- WHERE Office_Table.office_id = Employee_Office_Table.office_id AND Employee_Office_Table.emp_id = EMPLOYEE.emp_id) group by place;


-- 4.Get the third highest salaried employee name, insurance name and salary(Joins)


-- sql>>
-- SELECT * from (select EMPLOYEE.emp_name,Insurance_Table.insurance_name,Employee_Info_Table.salary
-- from EMPLOYEE 
-- INNER jOIN Insurance_Table
-- on EMPLOYEE.insurance_id = Insurance_Table.insurance_id
-- INNER JOIN Employee_Info_Table
-- ON EMPLOYEE.Id = Employee_Info_Table.Id) ORDER by salary DESC LIMIT 2,1;



-- 5.Get the Average salary of employees from each department for a particular location
-- SQL>>
-- Select place ,Dept_name,AVG(salary) as Average from( SELECT place,Emp_Name,Dept_Name, salary
-- FROM Employee
-- JOIN Department_Table
--  ON Employee.Dept_Id = Department_Table.Dept_Id
-- Join Employee_info_Table
-- on Employee.Id = Employee_info_Table.id
-- join Employee_Office_Table,Office_Table
-- On Employee.Emp_Id = Employee_Office_Table.Emp_id And 
-- Employee_Office_Table.Office_id = Office_Table.office_id)
-- group by place,Dept_Name ;


-- 6. Get the sum of salaries of the employees joined after 02-01-2022 working in ‘Madhapur’


-- SQL>>
-- SELECT sum(salary) as Total_salary FROM (SELECT place,Emp_Name,Dept_Name, salary,DOJ
-- FROM Employee
-- JOIN Department_Table
--  ON Employee.Dept_Id = Department_Table.Dept_Id
-- Join Employee_info_Table
-- on Employee.Id = Employee_info_Table.id
-- join Employee_Office_Table,Office_Table
-- On Employee.Emp_Id = Employee_Office_Table.Emp_id And 
-- Employee_Office_Table.Office_id = Office_Table.office_id)
-- WHERE DOJ > ('02-01-2022') And place = "Madapur" ;


-- 7. Get name, department, salary of employees who joined after 20-02-2022 and  salary is greater than 20000 and has insurance either in lic or icici or both and whose work location is madhapur.

-- SQL>>
-- SELECT emp_name, dept_name, salary FROM (SELECT Emp_Name,Dept_Name,place, salary,DOJ,Insurance_name
-- FROM Employee
-- JOIN Department_Table
--  ON Employee.Dept_Id = Department_Table.Dept_Id
-- Join Employee_info_Table
-- on Employee.Id = Employee_info_Table.id
-- join Insurance_Table
--  on Employee.Insurance_id = Insurance_Table.Insurance_id
-- join Employee_Office_Table,Office_Table
-- On Employee.Emp_Id = Employee_Office_Table.Emp_id And 
-- Employee_Office_Table.Office_id = Office_Table.office_id)
-- WHERE DOJ >('20-02-2022') And salary > 20000 And 
-- (Insurance_name ="Vehicle Insurance" or Insurance_name ="Life Insurance") And place = "Madhapur";


-- 8. Get the list of employee names and their respective immediate head name
--  SQL>>
-- SELECT EMP_NAME, (SELECT EMP_NAME FROM EMPLOYEE WHERE EMP_ID = EMP.IMMEDIATE_HEAD_ID) FROM EMPLOYEE EMP;


-- 9. Get the list of employee names and their respective immediate head name with  their respective department names.
--   Eg: emp name | emp department name | immediate head name | immediate head department name

-- SELECT EMP_NAME, (SELECT EMP_NAME FROM EMPLOYEE WHERE EMP_ID = EMP.IMMEDIATE_HEAD_ID) AS IMMEDIATE_HEAD_NAME,
-- DEP.DEPT_NAME , 
-- (SELECT IDEP.DEPT_NAME FROM EMPLOYEE IEMP
-- INNER JOIN DEPARTMENT_TABLE IDEP ON IEMP.DEPT_ID = IDEP.DEPT_ID
-- WHERE EMP_ID = EMP.IMMEDIATE_HEAD_ID) AS IMMEDIATE_HEAD_DEPT_NAME
-- FROM EMPLOYEE EMP
-- INNER JOIN DEPARTMENT_TABLE DEP ON EMP.DEPT_ID = DEP.DEPT_ID;


-- 10. Get the list of employees whose immediate head working in location ‘madhapur’
--        and whose salary is less than twice the salary of their respective immediate   heads.


-- select emp.emp_name from employee emp 
-- inner join employee_info_table EOT on emp.emp_id = EOT.id
-- where 2 * EOT.salary < (select EOT.salary from employee 
-- inner join employee_info_table EOT on emp.emp_id = EOT.id
-- where emp_id = emp.immediate_head_id
-- and emp.emp_id in (select emp_id from employee aemp
-- where aemp.emp_id in (
-- select EOT.id from employee_office_table EOT
-- inner join office_table OT on EOT.office_id = OT.office_id where OT.place = "Madhapur"
-- )));
