select et.emp_name, dt.dept_name, it.insurance_name
from employee_table et inner join department_table dt
on et.dept_id=dt.dept_id inner join insurance_table it 
on et.insurance_id=it.insurance_id;
