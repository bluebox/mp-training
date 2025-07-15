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
  INSERT INTO Departments(dept_id,dept_name) 
  VALUES (10,'AI_Research');

  SAVEPOINT sp_before_manager;
  SELECT "Managers executing";

  INSERT INTO DepartmentHeads(Emp_Id,dept_head_id,dept_id,dept_head_since) 
  VALUES (2978, 7, 10, '2025-01-01');

  SAVEPOINT sp_before_employees;
  SELECT "Employees executing";

  IF EXISTS (SELECT 1 FROM Employees WHERE Emp_Id=2998) THEN
  ROLLBACK TO sp_before_employees;
  SELECT "successfully rollbacked to sp_before_employees";
  ELSE
  INSERT INTO Employees(Emp_Id, Employee_Name, dob, Date_Joined, dept_id) 
  VALUES (2998, 'Rohit', '2000-02-03', '2024-01-10', 3);
  END IF;
  
  SELECT "Employees executing";
  INSERT INTO Employees(Emp_Id, Employee_Name, dob, Date_Joined, dept_id) 
  VALUES (3003, 'Vijay', '2000-09-06', '2024-01-15', 5);  

  COMMIT;
  SELECT 'Transaction completed successfully.' AS status;

END;
//
DELIMITER ;






