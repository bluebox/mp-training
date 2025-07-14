USE sys;
DELIMITER //

CREATE PROCEDURE ExampleTransaction()
BEGIN
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    ROLLBACK;
    SELECT 'SQL Exception occurred. Rolled back entire transaction.' AS status;
  END;

  DECLARE EXIT HANDLER FOR SQLWARNING
  BEGIN
    ROLLBACK;
    SELECT 'SQL Warning occurred. Rolled back entire transaction.' AS status;
  END;

  START TRANSACTION;


  SAVEPOINT sp_before_department;
  
  SELECT "Department executing";
  INSERT INTO Departments(dept_id, Manager_Id, dept_name) 
  VALUES (10, 5, 'AI_Research');

  SAVEPOINT sp_before_manager;
  SELECT "Managers executing";

  INSERT INTO Managers(Emp_Id, Manager_Id, dept_id, Manager_Since) 
  VALUES (3001, 11, 10, '2024-01-01');

  SAVEPOINT sp_before_employees;
  SELECT "Employees executing";

  IF EXISTS (SELECT 1 FROM Employees WHERE Emp_Id=2998) THEN
  SELECT "successfully rollbacked to sp_before_employees";
  ROLLBACK TO sp_before_employees;
  ELSE
  INSERT INTO Employees(Emp_Id, Employee_Name, age, Date_Joined, Manager_Id, dept_id) 
  VALUES (2998, 'Rohit', 25, '2024-01-10', 11, 10);
  END IF;
  
  SELECT "Employees executing";
  INSERT INTO Employees(Emp_Id, Employee_Name, age, Date_Joined, Manager_Id, dept_id) 
  VALUES (3003, 'Vijay', 35, '2024-01-15', 11, 10);  

  COMMIT;
  SELECT 'Transaction completed successfully.' AS status;

END;
//
DELIMITER ;






