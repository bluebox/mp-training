create table Department_Table (dept_id int primary key , dept_name varchar);
create table Insurance_Table (insurance_id int primary key , isurance_name varchar );
create table  Office_Table(office_id int primary key, office_name varchar , place varchar);
create table Employee_Info_Table(id int primary key , DOJ date , salary varchar );
create table  Employee_Table( emp_id int primary key, name varchar, immediate_head_id int ,dept_id int , insurance_id int ,   employee_info_id int , foreign key (dept_id) references Department_Table(dept_id), foreign key (insurance_id) references Insurance_Table(insurance_id),foreign key (employee_info_id) references Employee_Info_Table(id));
create table  Employee_Office_Table(id int primary key , emp_id int , office_id int ,foreign key (emp_id) references Employee_Table(emp_id),foreign key (office_id) references Office_Table(office_id));




INSERT INTO Department_Table (dept_id,dept_name) VALUES ('01', 'pharma'),('02', 'optival'), ('03', 'custom furnish'),('04', 'it');

INSERT INTO Office_Table  VALUES ('01', 'pharma', 'kukatplally'),('02', 'optival', 'hitechcity'),('03', 'custom furnish', 'madhapur'),('04', 'it', 'balnagar');

insert into Insurance_Table values(21,"lic"),(22,"medplus"),(23,"sbi");

insert into  Employee_Info_Table values (102,"13-6-2022",50000), (103,"7-7-2022",40000);

insert  into Employee_Table values (200,"samba",251,2,21,101), (201,"srikar",252,2,22,102), (203,"sahithi",253,2,21,103), (204,"bhargavi",252,2,23,101), (205,"harsha",251,2,21,103), (252,"maniteja",null,2,21,102), (251,"vaibhav",null,2,22,102);

insert into Employee_Office_Table values (2,200,2),(1,204,1),(3,203,2);


1.select e.name,d.dept_name, i.insurance_name from Employee_Table e join Department_Table d on e.dept_id=d.dept_id  join Insurance_table i on e.insurance_id=i.Insurance_id;

2.select e.emp_id from Employee_Table e join Employee_Office_Table as h on e.emp_id=h.emp_id join Office_Table as g on h.office_id=g.office_id where g.place="hitechcity";

3.select g.place,count(s.name) from Employee_Table as s join Employee_Office_Table as h on s.emp_id=h.emp_id join Office_Table as g on h.office_id=g.office_id group by g.place;

4.select e.name,i.insurance_name,t.salary from Employee_Table e join Insurance_table i on e.insurance_id=i.insurance_id join Employee_Info_Table t on e.employee_info=t.id ORDER BY t.salary desc LIMIT 3,1; 

5.select avg(i.salary) as avg,d.dept_name from Department_Table d join Employee_Table e on d.dept_id=e.dept_id join Employee_Info_Table i on i.id=e.employee_info join Employee_Office_Table o on o.emp_id=e.emp_id join Office_Table g on g.office_id=o.office_id
group by d.dept_id having g.place ="hitechcity"; 

6.select sum(i.salary) from Employee_Info_Table i join Employee_Table j on i.id=j.employee_info join Employee_Office_Table k on j.emp_id=k.emp_id join Office_Table l on l.office_id=k.office_id where l.place="hitechcity" and i.DOJ>"2022-01-02";

7.select i.name,j.salary,k.dept_name from Employee_Table i join Employee_Info_Table j on i.employee_info=j.id join Department_Table k on i.dept_id=k.dept_id join Insurance_table l on i.insurance_id=l.insurance_id join Office_Table m on m.place="hitechcity" where j.DOJ>"2022-02-20" and j.salary>20000 and (l.insurance_name="lic" or l.insurance_name="anand") 

8.select j.name,i.name as head from Employee_Table i join Employee_Table j on i.emp_id=j.immediate_head_id;

9.select k.name,l.dept_name,i.name as head,j.dept_name from Employee_Table i join Department_Table j on i.dept_id = j.dept_id join Employee_Table k on i.emp_id=k.immediate_head_id join Department_Table l on k.dept_id=l.dept_id;

10.select j.name from Employee_Table i join Employee_Table j on i.emp_id=j.immediate_head_id join Employee_Office_Table k on i.emp_id = k.emp_id join office_table l on k.office_id=l.office_id join Employee_Info_Table m on m.id=i.employee_info_id join Employee_Office_Table