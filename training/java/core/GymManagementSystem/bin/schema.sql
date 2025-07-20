CREATE DATABASE IF NOT EXISTS gym_db;
USE gym_db;

CREATE TABLE IF NOT EXISTS membership_plans (
    plan_id INT AUTO_INCREMENT PRIMARY KEY,
    plan_name VARCHAR(50) NOT NULL,
    duration_months INT NOT NULL,
    fee DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    plan_id INT,
    FOREIGN KEY (plan_id) REFERENCES membership_plans(plan_id)
);
