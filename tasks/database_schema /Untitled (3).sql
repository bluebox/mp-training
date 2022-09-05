CREATE TABLE `Student` (
  `Student_id` int PRIMARY KEY,
  `name` nvarchar(255),
  `mobile_number` nvarchar(255),
  `email` nvarchar(255),
  `branch_id` int,
  `hallticket_number` nvarchar(255),
  `fee_id` int
);

CREATE TABLE `ExamBranch` (
  `hallticket_number` nvarchar(255) PRIMARY KEY,
  `dueexams` int,
  `completedexams` int
);

CREATE TABLE `Branch` (
  `branch_id` int PRIMARY KEY,
  `hod_id` int
);

CREATE TABLE `Hod` (
  `hod_id` int PRIMARY KEY,
  `hod_name` nvarchar(255)
);

CREATE TABLE `Employee` (
  `Employee_id` int PRIMARY KEY,
  `Employee_name` nvarchar(255),
  `branch_id` int,
  `employee_salary` int
);

CREATE TABLE `fee_report` (
  `fee_id` int PRIMARY KEY,
  `total_fee` int,
  `due_fee` int,
  `fee_status` boolean
);

CREATE TABLE `Course` (
  `course_id` int PRIMARY KEY,
  `course_name` nvarchar(255),
  `Employee_id` int
);

CREATE TABLE `Student_Course` (
  `Student_id` int,
  `Course_id` int
);

CREATE TABLE `Student_attandance` (
  `Student_id` int,
  `attandance` int
);

ALTER TABLE `Student` ADD FOREIGN KEY (`Student_id`) REFERENCES `Student_attandance` (`Student_id`);

ALTER TABLE `Student` ADD FOREIGN KEY (`branch_id`) REFERENCES `Branch` (`branch_id`);

ALTER TABLE `Student` ADD FOREIGN KEY (`hallticket_number`) REFERENCES `ExamBranch` (`hallticket_number`);

ALTER TABLE `Hod` ADD FOREIGN KEY (`hod_id`) REFERENCES `Branch` (`hod_id`);

ALTER TABLE `Student` ADD FOREIGN KEY (`fee_id`) REFERENCES `fee_report` (`fee_id`);

ALTER TABLE `Employee` ADD FOREIGN KEY (`Employee_id`) REFERENCES `Course` (`Employee_id`);

ALTER TABLE `Student` ADD FOREIGN KEY (`Student_id`) REFERENCES `Student_Course` (`Student_id`);

ALTER TABLE `Course` ADD FOREIGN KEY (`course_id`) REFERENCES `Student_Course` (`Course_id`);
