select dt.dept_name, round(avg(eit.salary)) as avg_salary
from employee_table et inner join employee_info_table eit
on et.employee_info=eit.id inner join department_table dt
on et.dept_id=dt.dept_id group by dt.dept_name;
