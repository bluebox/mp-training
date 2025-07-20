CREATE DATABASE Gym;
use Gym;


select database();

CREATE TABLE membership_plans (
    plan_id INT AUTO_INCREMENT PRIMARY KEY,
    plan_name VARCHAR(50) NOT NULL UNIQUE,
    duration_months INT NOT NULL,
    fee DECIMAL(10, 2) NOT NULL
);

CREATE TABLE members (
    member_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    plan_id INT,
    FOREIGN KEY (plan_id) REFERENCES membership_plans(plan_id) ON DELETE SET NULL ON UPDATE CASCADE
);

