

CREATE TABLE teacher (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    level CHAR(2) NOT NULL CHECK (level IN ('p', 'h', 'l')),
    joining_date datetime DEFAULT current_timestamp NOT NULL,
    experience INT NOT NULL
);

create table classes (
    Class_id int NOT NULL,
    Section char(3) NOT NULL,
    teacher_id int NOT NULL,
    primary key (Class_id),
    unique (Class_id, Section),
    foreign key  (teacher_id) references teacher(id) on delete cascade
);

CREATE TABLE student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Age INT NOT NULL,
    Class_id INT NOT NULL,
    Section CHAR(3) NOT NULL,
    joining_date datetime DEFAULT current_timestamp NOT NULL,
    attendance INT NOT NULL,
    FOREIGN KEY (Class_id) REFERENCES classes(Class_id) ON DELETE CASCADE
);

create table subject (id int auto_increment primary key,
    Name varchar(100) NOT NULL,
    Class_id int NOT NULL,
    Teacher_id int NOT NULL,
    foreign key (Class_id) references classes(Class_id) on delete cascade,
    foreign key (Teacher_id) references teacher(id) on delete cascade);

CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Class_id INT NOT NULL,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    grade INT NOT NULL,
    percentage FLOAT NOT NULL,
    FOREIGN KEY (Class_id) REFERENCES classes(Class_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject(id) ON DELETE CASCADE
);


insert into teacher (Name, level, experience) VALUES
('Alice Johnson', 'p', 5),
('Bob Smith', 'h', 12),
('Charlie Lee', 'l', 3),
('David Miller', 'h', 8),
('Emma Thomas', 'p', 6),
('Sophie Turner', 'l', 4),
('George Baker', 'p', 10);

insert into classes (Class_id, Section, teacher_id) VALUES
(101, 'A', 1),
(102, 'B', 2),
(103, 'C', 3),
(104, 'D', 4),
(105, 'E', 5),
(106, 'F', 6),
(107, 'G', 7);

insert into student (Name, Age, Class_id, Section, attendance) VALUES
('John Doe', 12, 101, 'A', 95),
('Jane Roe', 13, 101, 'A', 90),
('Max Payne', 12, 102, 'B', 85),
('Lily Potter', 13, 103, 'C', 88),
('Sam Wilson', 12, 104, 'D', 88),
('Peter Parker', 14, 105, 'E', 93),
('Tony Stark', 13, 105, 'E', 97),
('Bruce Wayne', 14, 106, 'F', 85),
('Clark Kent', 13, 107, 'G', 80),
('Diana Prince', 12, 104, 'D', 89),
('Barry Allen', 13, 104, 'D', 92);

insert into subject (Name, Class_id, Teacher_id) VALUES
('Mathematics', 101, 1),
('Science', 102, 2),
('English', 103, 3),
('History', 101, 2),
('Geography', 103, 1),
('Computer Science', 104, 4),
('Physics', 105, 5),
('Chemistry', 105, 5),
('Biology', 106, 6),
('Social Studies', 107, 7),
('Art', 104, 4);

insert into results (Class_id, student_id, subject_id, grade, percentage) VALUES
(101, 1, 1, 9, 89.5),
(101, 2, 1, 8, 82.0),
(102, 3, 2, 7, 78.0),
(103, 4, 3, 10, 91.0),
(101, 1, 4, 6, 65.5),
(103, 4, 5, 9, 87.0),
(104, 5, 6, 9, 88.0),
(104, 6, 6, 10, 92.5),
(104, 7, 6, 8, 85.0),
(105, 8, 7, 9, 90.0),
(105, 9, 8, 10, 94.0),
(106, 10, 9, 7, 78.5),
(107, 11, 10, 6, 69.0),
(104, 7, 11, 9, 91.0),
(101, 1, 4, 5, 55.0),
(105, 8, 7, 7, 74.0),
(107, 11, 10, 8, 81.0),
(104, 6, 11, 10, 93.0);

insert into student_profile values(1,"Truman Doe","Alis Doe",45,42,"Abids, Hyderabad",1);
insert into student_profile values(2,"larry Potter","jane Potter",53,53,"Wadala, Mumbai",4),
(3,"Stark Wayne","Penny Wayne",47,48,"Hauz khaas, Delhi",8),
(4,"Phyllip duke","Samantha duke",45,37,"Anna nagar, Chennai",10),
(5,"Jake Wilson","Rose Wilson",40,38,"James Street, Hyderabad",5);

-- INNER JOIN – Students with Class and Teacher
select s.Name as student_name, s.Age, c.Class_id, c.Section, t.Name as teacher_name
from student s
inner join classes c on s.Class_id = c.Class_id
inner join teacher t on c.teacher_id = t.id;

-- LEFT JOIN – All Students and Results (if any)

select s.Name as student_name, r.grade, r.percentage
from student s
left join results r on s.id = r.student_id;

-- RIGHT JOIN – Subjects and Teachers 
select s.name as subject_name,t.name as teacher_name 
from subject s right join teacher t on s.Teacher_id = t.id;

-- FULL JOIN Simulation – Students and Results 

select s.Name as student_name, r.grade, r.percentage
from student s 
left join results r on s.id = r.student_id
union 
select s.Name as student_name, r.grade, r.percentage
from student s
right join results r on s.id = r.student_id;

-- GROUP BY – Average Result by Class

select c.class_id, avg(r.Percentage) as avg_percentage
from results r
join classes c on r.Class_id = c.Class_id
group by c.Class_id;

-- GROUP BY + HAVING – Only if Avg > 80

select c.Class_id, avg(r.percentage) as avg_percentage
from classes c
join results r on c.Class_id = c.Class_id
group by c.Class_id
having avg_percentage >80;


-- ORDER BY – Students by Attendance
select name, attendance
from student
order by attendance desc; 

-- Subquery – Students Above Class Average

select s.name, r.percentage from student s join results r on s.id =r.student_id
where r.percentage>(
select avg(r1.percentage)from results r1 where r1.Class_id = r.Class_id);

-- Subquery in from - avg score by subject
select subject_name,avg_score from ( select s.name as subject_name, avg(r.percentage) as avg_score from results r join subject s on r.subject_id = s.id group by s.id ) as subject_avg;

-- Subquery in select - subject count per class
select c.Class_id, (select count(*) from subject s where s.Class_id = c.Class_id) as subject_count from classes c;

-- one to many class to students
select c.Class_id, c.section,s.Name as student_name from classes c join student s on s.Class_id = c.Class_id; 

-- many to many Teachers and subjects
select t.Name as teacher_name, s.Name as subject_name from teacher t join subject s on s.teacher_id = t.id;

-- select * from student;



-- one to one student to studentprofile 

select * from student s join student_profile sp on 
s.id = sp.student_id;