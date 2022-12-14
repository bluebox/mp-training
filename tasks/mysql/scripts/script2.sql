select et.emp_name, dt.dept_name, ot.place 
from employee_table et inner join employee_office_table eot
on et.emp_id=eot.emp_id inner join office_table ot
on eot.office_id=ot.office_id inner join department_table dt 
on et.dept_id=dt.dept_id;
