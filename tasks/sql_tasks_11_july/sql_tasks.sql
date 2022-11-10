create table emp_table (
  emp_id int PRIMARY KEY,
  name varchar(255),
  immediate_head_id int,
  dept_id int, 
  insur_id int,
  emp_info int,
--   insurance_id int FOREIGN KEY REFERENCES insur_table(insur_id),
--   employee_info int FOREIGN KEY REFERENCES emp_info_table(id)
  FOREIGN KEY (dept_id) REFERENCES dept_table(dept_id),
  FOREIGN KEY (insur_id) REFERENCES insur_table(insur_id),
  FOREIGN KEY (emp_info) REFERENCES emp_info_table(id)
 
);

INSERT INTO emp_table VALUES (1, 'sairam', 7, 1, 1, 1);
INSERT INTO emp_table VALUES (2, 'nikas', 7, 1, 2, 2);
INSERT INTO emp_table VALUES (3, 'shashi', 8, 2, 1, 3);
INSERT INTO emp_table VALUES (4, 'arun', 9, 3, 2, 4);
INSERT INTO emp_table VALUES (5, 'venkatsesh', 9, 3, 2, 5);
INSERT INTO emp_table VALUES (6, 'yakshith', 8, 2, 2, 6);
INSERT INTO emp_table VALUES (7, 'vaibhav sir', null, 1, 2, 7);
INSERT INTO emp_table VALUES (8, 'hari sir', null, 2, 1, 8);
INSERT INTO emp_table VALUES (9, 'dheeraj sir', null, 3, 1, 9);

create Table dept_table (
  dept_id int PRIMARY KEY,
  dept_name varchar
);

create Table insur_table (
  insur_id int PRIMARY KEY,
  insur_name varchar
);

create Table office_table (
  off_id int PRIMARY KEY,
  off_name varchar,
  place varchar
);

INSERT INTO office_table (off_id,off_name,place) VALUES (1, 'head_office medplus', 'hitech city');
INSERT INTO office_table (off_id,off_name,place) VALUES (2, 'medplus branch1', 'madhapur');
INSERT INTO office_table (off_id,off_name,place) VALUES (3, 'medplus branch2', 'banjara hills');

CREATE Table emp_off_table (
  id int PRIMARY KEY,
  emp_id int,
  off_id int,
--   emp_id int FOREIGN KEY REFERENCES emp_table(emp_id),
--   off_id int FOREIGN KEY REFERENCES office_table(off_id),
  FOREIGN KEY (emp_id) REFERENCES emp_table(emp_id),
  FOREIGN KEY (off_id) REFERENCES office_table(off_id)
);

INSERT INTO emp_off_table (id,emp_id,off_id) VALUES (1, 1, 1);
INSERT INTO emp_off_table (id,emp_id,off_id) VALUES (2, 2, 1);
INSERT INTO emp_off_table (id,emp_id,off_id) VALUES (3, 3, 2);
INSERT INTO emp_off_table (id,emp_id,off_id) VALUES (4, 4, 3);
INSERT INTO emp_off_table (id,emp_id,off_id) VALUES (5, 5, 3);
INSERT INTO emp_off_table (id,emp_id,off_id) VALUES (6, 6, 2);
INSERT INTO emp_off_table (id,emp_id,off_id) VALUES (7, 7, 2);
INSERT INTO emp_off_table (id,emp_id,off_id) VALUES (8, 8, 1);
INSERT INTO emp_off_table (id,emp_id,off_id) VALUES (9, 9, 1);


create Table emp_info_table (
  id int PRIMARY KEY,
  doj date,
  salary int
);

INSERT INTO emp_info_table (id,doj,salary) VALUES (1, '2022-11-22', 90000);
INSERT INTO emp_info_table (id,doj,salary) VALUES (2, '2022-11-22', 80000);
INSERT INTO emp_info_table (id,doj,salary) VALUES (3, '2022-11-22', 100000);
INSERT INTO emp_info_table (id,doj,salary) VALUES (4, '2022-11-22', 70000);
INSERT INTO emp_info_table (id,doj,salary) VALUES (5, '2022-11-22', 60000);
INSERT INTO emp_info_table (id,doj,salary) VALUES (6, '2022-11-22', 60000);
INSERT INTO emp_info_table (id,doj,salary) VALUES (7, '2022-11-22', 70000);
INSERT INTO emp_info_table (id,doj,salary) VALUES (8, '2022-11-22', 50000);
INSERT INTO emp_info_table (id,doj,salary) VALUES (9, '2022-11-22', 70000);


INSERT INTO dept_table VALUES (1, 'python1');
INSERT INTO dept_table VALUES (2, 'python2');
INSERT INTO dept_table VALUES (3, 'python3');

INSERT INTO insur_table VALUES (1, 'LIC');
INSERT INTO insur_table VALUES (2, 'ICICI');

-- 1
-- select emp.name, dep.dept_name, ins.insur_name from emp_table emp
-- inner join dept_table dep on emp.dept_id = dep.dept_id
-- inner join insur_table ins on emp.insur_id = ins.insur_id;

-- 2
-- select emp.name, dep.dept_name from emp_table emp
-- inner join dept_table dep on emp.dept_id = dep.dept_id
-- where emp.emp_id in (select emp_id from emp_off_table
-- EOT inner join office_table OT on EOT.off_id = OT.off_id
-- where place = "madhapur");

-- 3
-- select count(EOT.off_id), OT.place from emp_off_table EOT
-- inner join office_table OT on EOT.off_id = OT.off_id
-- group by EOT.off_id

-- 4
-- select emp.name, ins.insur_name, empInfo.salary
-- from emp_table emp
-- inner join insur_table ins on emp.insur_id = ins.insur_id
-- inner join emp_info_table empInfo on emp.emp_info = empInfo.id
-- where emp.emp_info in (
-- select id as emp_info from (select id, salary from emp_info_table
-- order by salary desc limit 3
-- ) order by salary limit 1)

-- 5
-- SELECT AVG(SALARY )  FROM (select * from emp_table JOIN dept_table ON emp_table.dept_id=dept_table.dept_id
-- JOIN emp_info_table ON emp_table.emp_info=emp_info_table.id
-- JOIN (SELECT * FROM emp_off_table JOIN office_table ON emp_off_table.off_id=office_table.off_id) AS TJ
-- ON emp_table.emp_id=TJ.emp_id) where place ="madhapur" GROUP BY dept_id

-- 6
-- select sum(EOT.salary) from emp_table emp
-- inner join emp_info_table EOT on emp.emp_info = EOT.id
-- where EOT.doj > '2022-01-02' and emp.emp_id in (
-- select EOT.id from emp_off_table EOT
-- inner join office_table OT on EOT.off_id = OT.off_id where OT.place = "madhapur"
-- )

-- 7
-- select emp.name, dep.dept_name, EOT.salary from emp_table emp
-- inner join emp_info_table EOT on emp.emp_info = EOT.id
-- inner join insur_table ins on emp.insur_id = ins.insur_id
-- inner join dept_table dep on emp.dept_id = dep.dept_id
-- where ins.insur_name in ("LIC", "ICICI") and EOT.doj > '2022-01-02' and EOT.salary > 5000 and emp.emp_id in (
-- select EOT.id from emp_off_table EOT
-- inner join office_table OT on EOT.off_id = OT.off_id where OT.place = "madhapur"
-- )

-- 8
-- select emp.name, copyemp.name from emp_table emp
-- inner join emp_table copyemp where emp.immediate_head_id = copyemp.emp_id
-- select emp.name, (select name from emp_table where emp_id = emp.immediate_head_id) from emp_table emp

-- 9
-- select name,dt.dept_name ,(select name from emp_table
-- where emp_id=emp.immediate_head_id), (select di.dept_name from emp_table empp
-- inner join dept_table di on empp.dept_id = di.dept_id
-- where emp_id = emp.immediate_head_id) from emp_table emp
-- inner join dept_table dt on emp.dept_id = dt.dept_id


-- 10
-- select emp.name from emp_table emp 
-- inner join emp_info_table EOT on emp.emp_id = EOT.id
-- where 2 * EOT.salary < (select EOT.salary from emp_table 
-- inner join emp_info_table EOT on emp.emp_id = EOT.id
-- where emp_id = emp.immediate_head_id
-- and emp.emp_id in (select emp_id from emp_table aemp
-- where aemp.emp_id in (
-- select EOT.id from emp_off_table EOT
-- inner join office_table OT on EOT.off_id = OT.off_id where OT.place = "madhapur"
-- )))
