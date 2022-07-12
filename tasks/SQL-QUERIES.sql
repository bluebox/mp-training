CREATE TABLE Employee_Table(
  emp_id INT NOT NULL PRIMARY KEY,
  name VARCHAR(250),
  immediate_head_id INT,
  dept_id INT,
  insurance_id INT,
  employee_info INT,
  FOREIGN KEY(dept_id) REFERENCES Department_Table(dept_id) ON DELETE CASCADE,
  FOREIGN KEY(insurance_id) REFERENCES Insurance_Table(insurance_id) ON DELETE CASCADE,
  FOREIGN KEY(employee_info) REFERENCES Employee_Info_Table(id) ON DELETE CASCADE
  )
  
INSERT INTO Employee_Table
	(emp_id, name, immediate_head_id, dept_id, insurance_id, employee_info)
VALUES
	(1, "hari", 10, 2, 3, 1),
	(2, "shyam", 9, 2, 1, 1),
	(3, "harsha", 8, 3, 2, 4),
	(4, "achyuth", 7, 1, 3, 6),
	(5, "dhanush", 6, 2, 2, 3),
	(6, "tharun", 5, 3, 1, 5)

  
  
CREATE TABLE Department_Table(
	dept_id INT NOT NULL PRIMARY KEY,
	dept_name VARCHAR(250)

  )

INSERT INTO Department_Table
	(dept_id,dept_name)
VALUES
	(1, "HR"),
	(2, "Software"),
	(3, "Sales")

  
  
  
  
CREATE TABLE Insurance_Table
	(
	insurance_id INT NOT NULL PRIMARY KEY,
	insurance_name VARCHAR(250)

  	)	
INSERT INTO Insurance_Table
	(insurance_id, insurance_name)
VALUES
	(1, "Life insurance"),
	(2, "Accident insurance"),
	(3, "Health insurance")

	  
  
CREATE TABLE Office_Table(
  office_id INT NOT NULL PRIMARY KEY,
  office_name VARCHAR(250),
  place VARCHAR(250)
  
  )
INSERT INTO Office_Table
	(office_id, office_name, place)
VALUES
	(1, "Medplus software solutions", "Hytec city"),
	(2, "Medplus Pharmaceuticals", "Gayatri hills"),
	(3, "Medplus corporate office", "Kukatpally"),
	(4, "Medplus", "Sanath Nagar"),
	(5, "Medplus Pharmaceuticals", "Jubilee hills"),
	(6, "Medplus corporate office", "Madhapur")
  
  
  
  
CREATE TABLE Employee_Office_Table(
  id INT NOT NULL PRIMARY KEY,
  emp_id INT,
  office_id INT,
  FOREIGN KEY(emp_id) REFERENCES Employee_Table(emp_id) ON DELETE CASCADE,
  FOREIGN KEY(office_id) REFERENCES Office_Table(office_id) ON DELETE CASCADE
  
  )
INSERT INTO Employee_Office_Table
	(id, emp_id, office_id)
VALUES
	(1, 2, 1),
	(2, 1, 3),
	(3, 4, 2),
	(4, 5, 6),
	(5, 6, 4),
	(6, 3, 5)

CREATE TABLE Employee_Info_Table(
  id INT NOT NULL PRIMARY KEY,
  date_of_joining DATE,
  salary INT
  
  
  )
INSERT INTO Employee_Info_Table
			(id, date_of_joining, salary)
VALUES
	(1, "06-06-2022", 50000),
	(2, "25-06-2022", 40000),
	(3, "11-07-2022", 45000),
	(4, "06-06-2021", 30000),
	(5, "25-06-2020", 25000),
	(6, "11-07-2029", 20000)

    




 
 
 
 --QUERY --1
 
 
SELECT 
	Employee_Table.name as Name,
	Department_Table.dept_name as department_name, 
	Insurance_Table.insurance_name as name_of_insurance

			
FROM (Employee_Table NATURAL JOIN Insurance_Table)
	As new NATURAL JOIN Department_Table  
    
    
--QUERY -- 2

SELECT 
	Department_Table.dept_name as department_list,
	Employee_Table.name As name_of_employee
FROM
	(Employee_Table NATURAL JOIN Department_Table) AS T1
	NATURAL JOIN Employee_Office_Table AS T2
	NATURAL JOIN Office_Table
WHERE

	Office_Table.place = "Kukatpally"
	
	
--QUERY -- 3


SELECT 
	COUNT(Employee_Table.name) AS count_of_employee,
	Office_Table.place AS location
FROM
	(Employee_Table NATURAL JOIN Employee_Office_Table) AS T1
	NATURAL JOIN Office_Table
GROUP BY
  	Office_Table.place
  	
 
--QUERY -- 4



SELECT 
	Employee_Table.name AS name_of_employee,
	Employee_Info_Table.salary AS salary
FROM
	(Employee_Info_Table NATURAL JOIN Employee_Office_Table) AS T1
	NATURAL JOIN Employee_Table
ORDER BY
	Employee_Info_Table.salary DESC
LIMIT 3;


--QUERY -- 5


SELECT 
	AVG(salary) AS avg_salary,
	Employee_Table.dept_id AS department_name
FROM
	(Employee_Info_Table NATURAL JOIN Employee_Office_Table) AS T1
	NATURAL JOIN Employee_Table
GROUP BY
	Employee_Table.dept_id



--QUERY --- 6

SELECT 
	AVG(salary) as avg_salary
	FROM Employee_Info_Table
WHERE 
	strftime("%Y", date_of_joining) = "2022" 
GROUP BY 
 	strftime("%Y", date_of_joining) 
    
    
 --QUERY -- 7
 
SELECT 
	Employee_Table.name as name,
	Department_Table.dept_name as department_name 

FROM 
	((Employee_Table NATURAL JOIN Department_Table ) as new
	NATURAL JOIN Employee_Office_Table) as another 
	NATURAL JOIN Employee_Info_Table
WHERE 
	salary > 20000 and insurance_id in (1, 3)
	
	
	
	
--QUERY -- 8


SELECT 
	a.name as name_of_employee,
	b.name as name_of_immediate_head
FROM
	Employee_Table a join Employee_Table b on a.immediate_head_id = b.emp_id



--QUERY -- 9


SELECT 
	a.name as name_of_employee,
	b.name as name_of_immediate_head,
	Department_Table.dept_name as name_of_deparatment
FROM
	(Employee_Table a join Employee_Table b on a.immediate_head_id = b.emp_id) as new INNER JOIN Department_Table ON new.dept_id = Department_Table.dept_id


--QUERY -- 10
SELECT 
	a.name,
	b.name as Head_name,
	c.salary,
	d.salary as Head_salary,
	f.place as head_place

FROM
Employee_Table a 
join  Employee_Table b on b.emp_id=a.immediate_head_id 
join Employee_Info_Table c on c.id=a.employee_info 
join Employee_Info_Table d on d.id=b.employee_info 
join Employee_Office_Table e on e.emp_id=b.emp_id 
join Office_Table f on f.office_id= e.office_id 
where f.place ="hitech city"  and d.salary > 2*c.salary;
