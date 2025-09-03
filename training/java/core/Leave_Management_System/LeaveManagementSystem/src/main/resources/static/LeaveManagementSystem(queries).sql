create database LMS;
use lms;
CREATE TABLE employees(
employeeId INT PRIMARY KEY AUTO_INCREMENT,
employeeName VARCHAR(255) NOT NULL,
employeeEmail VARCHAR(255) NOT NULL UNIQUE,
employeeRole VARCHAR(30) NOT NULL CHECK (employeeRole IN ('ADMIN', 'MANAGER','EMPLOYEE')),
employeeMobile_No VARCHAR(15) NOT NULL UNIQUE,
employeeDOB date NOT NULL,
employeeDOJ date NOT NULL,
employeeGender VARCHAR(1) NOT NULL CHECK (employeeGender IN ('M','F')),
employeeAddress VARCHAR(255), 
reporterId INT,
FOREIGN KEY (reporterId) REFERENCES employees(employeeId)
);


CREATE TABLE leave_requests(
requestId INT PRIMARY KEY AUTO_INCREMENT,
employeeId INT NOT NULL,
leaveType VARCHAR(30) NOT NULL CHECK (leaveType IN ('CASUAL_LEAVE','SICK_LEAVE','EARNED_LEAVE','OPTIONAL_LEAVE')),
startDate DATE NOT NULL,
endDate DATE NOT NULL,
remarks VARCHAR(255) NOT NULL,
leaveStatus VARCHAR(30) NOT NULL CHECK(leaveStatus IN ('ACCEPTED','REJECTED','CANCELLED','PENDING')),
rejectionReason VARCHAR(255),
FOREIGN KEY (employeeId) REFERENCES employees(employeeId)
);

CREATE TABLE attendence(
id INT PRIMARY KEY AUTO_INCREMENT,
employeeId INT NOT NULL,
date DATE NOT NULL,
checkIn time,
checkOut time,
workingHRS DECIMAL(4,2) GENERATED ALWAYS AS (TIMESTAMPDIFF(MINUTE, checkIn, checkOut) / 60) STORED,
attendenceStatus VARCHAR(30), 
FOREIGN KEY (employeeId) REFERENCES employees(employeeId)
);

CREATE TABLE exit_requests(
requestId INT PRIMARY KEY AUTO_INCREMENT,
employeeId INT NOT NULL,
exitDate DATE NOT NULL,
requestStatus VARCHAR(30) NOT NULL CHECK(requestStatus IN ('ACCEPTED','REJECTED','CANCELLED','PENDING')),
requestSubmittedAt timestamp NOT NULL,
FOREIGN KEY (employeeId) REFERENCES employees(employeeId)
);

CREATE TABLE leaves(
id INT PRIMARY KEY AUTO_INCREMENT,
employeeId INT NOT NULL,
CasualLeaves INT NOT NULL,
EarnedLeaves INT NOT NULL, 
SickLeaves INT NOT NULL, 
OptionalLeaves INT NOT NULL, 
FOREIGN KEY (employeeId) REFERENCES employees(employeeId)
);


CREATE TABLE users(
employeeId INT PRIMARY KEY NOT NULL,
userName VARCHAR(255) NOT NULL,
userPassword VARCHAR(255) NOT NULL,
userRole VARCHAR(30) NOT NULL CHECK (userRole IN ('ADMIN', 'MANAGER','EMPLOYEE')),
FOREIGN KEY (employeeId) REFERENCES employees(employeeId)
);


select * from attendence;
show create table attendence;
select * from employees;
