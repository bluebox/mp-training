select et1.emp_name as 'emp name', dt.dept_name as 'emp department name', 
ifnull(et2.emp_name, 'Senior most employee in the current office') as 'immediate head name', dt.dept_name as 'immediate head department name'
from employee_table et1 left join employee_table et2
on et1.immediate_head_id=et2.emp_id inner join department_table as dt
on et1.dept_id=dt.dept_id 