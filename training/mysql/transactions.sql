use sys;
DELIMITER //

CREATE PROCEDURE FullDeptSetupTransaction()
BEGIN
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    ROLLBACK;
    SELECT 'SQL Exception occurred. Rolled back entire transaction.' AS status;
  END;

  SET @simulate_error = FALSE;
  SET @last_manager_id = 12;
  SET @new_dept_id = 9;
  SET @today = CURDATE();

  START TRANSACTION;


  INSERT INTO Departments(dept_id, Manager_Id, dept_name)
  VALUES (@new_dept_id, @last_manager_id, 'Data_Engineering');

  SAVEPOINT sp_dept;


  INSERT INTO Managers(Emp_Id, Manager_Id, dept_id, Manager_Since)
  VALUES (3001, @last_manager_id, @new_dept_id, @today);

  SAVEPOINT sp_manager;


  INSERT INTO Employees(Emp_Id, Employee_Name, age, Date_Joined, Manager_Id, dept_id)
  VALUES 
  (3002, 'Tina', 25, NOW(), @last_manager_id, @new_dept_id),
  (900, 'InvalidAgeUser', -1, NOW(), @last_manager_id, @new_dept_id); 

  SAVEPOINT sp_employees;


  IF EXISTS (SELECT 1 FROM Employees WHERE dept_id = @new_dept_id AND age <= 0) THEN
    SET @simulate_error = TRUE;
    ROLLBACK TO sp_manager;
    DELETE FROM Departments WHERE dept_id = @new_dept_id;
    SELECT 'Invalid employee age detected. Rolled back to manager insertion.' AS status;
    ROLLBACK;
    LEAVE proc;
  END IF;

  INSERT INTO PayScale(dept_id, designation, `salary( in $)`)
  VALUES
  (@new_dept_id, 'associate', 2400),
  (@new_dept_id, 'senior associate', 900), 
  (@new_dept_id, 'junior associate', 2300);

  SAVEPOINT sp_payscale;


  IF EXISTS (SELECT 1 FROM PayScale WHERE dept_id = @new_dept_id AND `salary( in $)` < 1000) THEN
    SET @simulate_error = TRUE;
    ROLLBACK TO sp_employees;
    DELETE FROM Employees WHERE dept_id = @new_dept_id;
    DELETE FROM Managers WHERE dept_id = @new_dept_id;
    DELETE FROM Departments WHERE dept_id = @new_dept_id;
    SELECT 'Salary too low. Rolled back to employee insertion.' AS status;
    ROLLBACK;
    LEAVE proc;
  END IF;


  COMMIT;
  SELECT 'All changes successful. Committed to database.' AS status;

END //

DELIMITER ;
