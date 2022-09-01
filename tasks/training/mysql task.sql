show databases;

create database college;

use college;

-- DDL (Data definition language)
-- many to one relationship


 create table department(dept_id varchar(5) primary key , dept_name  varchar(30) not null , HOD varchar(50)
);
create table student(firstname varchar(30) , lastname varchar(30) , usn varchar(10) ,
 semester int , phone_number varchar(10) , dept_id varchar(5),
 constraint foreign key(dept_id) REFERENCES department(dept_id) , primary key ( usn)

 );
 
 create table address( addr_id int auto_increment primary key , street varchar(40) , city varchar(40) , state varchar(30)   );
 
 -- alter student table to add foriegn key
 alter table student add column addr_id int;
 alter table student add foreign key (addr_id) references address(addr_id);




create table subjects ( subject_code varchar(6) primary key , sub_name varchar(30) , lecturer varchar(50) );
-- to drop table
drop table student;
 

-- Many to Many relationship between student and subjects

create table student_sub( ref_id int primary key auto_increment , 
usn varchar(10),
subject_code varchar(6) ,
foreign key(usn) references student(usn),
foreign key(subject_code) references subjects(subject_code)
);
-- alter

alter table student rename column phone_number to mobile;

-- truncate

 truncate student;
 
 



-- DML

 -- INSERT

-- insert to department
insert into department values('CSE','computer science' , "Pradeep");
insert into department values('ECE','Electronics and communication' , "Aishwarya");
insert into department values('ME','Mechanical' , "Manasa");
insert into department values('CIV','Civil' , "Rakesh");
insert into department values('TX','Textile' , "Darshan");

-- insert to subjects
insert into subjects values('18CS90','IOT' , "Pradeep");
insert into subjects values('18CS91','Web Dev' , "Prathibha");
insert into subjects values('18CS92','ACA' , "Shylesh");
insert into subjects values('18CS93','Python' , "Yamuna");
insert into subjects values('18CS94','DSA' , "Harsha");



-- insert into address

insert into address(street,city,state) values('patrika nagar','Hyderabad' , "Telangana");
insert into address(street,city,state) values('MG Road','Bangalore' , "Karnataka");
insert into address(street,city,state) values('DLF','Kakinada' , "Andhara Pradesh");
insert into address(street,city,state) values('Church street','Bangalore' , "Karnataka");
insert into address(street,city,state) values('Charminar','Hyderabad' , "Telangana");


-- insert into student
insert into student values("aishwarya" , 'Dayanand', '1SK18CS001', 8 ,'9148334723','CSE',1);
insert into student values("Manasa" , 'Veerla', '1SK18EC002', 7 ,'9148334724','ECE',2);
insert into student values("Rakesh" , 'Bandi', '1SK18EC001', 5,'9148334725','ECE',3);
insert into student values("Sushmitha" , 'Venkatesh', '1SK18CS042', 8 ,'9148334726','CSE',3);
insert into student values("Tejaswini" , 'Srinivas', '1SK18CS046', 8 ,'9148334727','CIV',5);
insert into student values("Anitha" , 'S', '1SK18ME003', 3 ,'9148334727','ME',2);
insert into student values("Bala" , 'Manikanta', '1SK18CV001', 3 ,'9148334738','CIV',4);
insert into student values("Nava" , 'Vinay', '1SK18ME001', 5 ,'9148334790','ME',1);
insert into student values("Rahul" , 'Rao', '1SK18TX001', 7 ,'9148334732','TX',3);
insert into student values("Vinod" , 'pathange', '1SK18TX002', 1 ,'9148334720','TX',5);
insert into student values("Harsha" , 'pasala', '1SK18CS034', 8 ,'9148334767','CSE',5);


SELECT * FROM student_sub;
truncate student_sub;
-- insert into student_sub


insert into student_sub(usn,subject_code) values('1SK18CS001','18CS90');
insert into student_sub(usn,subject_code) values('1SK18CS001','18CS91');
insert into student_sub(usn,subject_code) values('1SK18CS001','18CS92');
insert into student_sub(usn,subject_code) values('1SK18CS042','18CS92');
insert into student_sub(usn,subject_code) values('1SK18CS046','18CS94');
insert into student_sub(usn,subject_code) values('1SK18CS042','18CS92');
insert into student_sub(usn,subject_code) values('1SK18ME003','18CS93');
insert into student_sub(usn,subject_code) values('1SK18CV001','18CS93');
insert into student_sub(usn,subject_code) values('1SK18EC001','18CS91');
insert into student_sub(usn,subject_code) values('1SK18EC001','18CS90');
insert into student_sub(usn,subject_code) values('1SK18ME001','18CS94');
insert into student_sub(usn,subject_code) values('1SK18CS034','18CS94');

 -- delete
 
 delete from student where semester > 8;
 
 
-- update 

update student_sub set subject_code = '18CS94' where ref_id = 10;


-- QUERIES

-- SELECT ALL STUDENTS 

SELECT * FROM student;

-- select all students from semester 8
select * from student where semester = 8;

-- show number of students from each branch and semester

select dept_id , semester , count(*) as no_of_students from student group by dept_id , semester;



-- show department details of students from cse department;

select s.firstname , s.lastname , s.usn , d.dept_name , d.HOD from student s inner join department d on s.dept_id = d.dept_id 
where s.dept_id = 'CSE';

-- display students details and subjects they are enrolled in along with department details

select s.firstname , s.lastname ,  sub.sub_name , sub.lecturer ,d.dept_name , d.HOD 
from student_sub stu_sub inner join student s on stu_sub.usn = s.usn 
inner join department d on d.dept_id  = s.dept_id 
inner join subjects sub on stu_sub.subject_code = sub.subject_code;






