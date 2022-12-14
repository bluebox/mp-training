SELECT count(emp_id) from Employee_Office_Table et inner join Office_Table ot
on et.office_id = ot.office_id where place = 'AMB';