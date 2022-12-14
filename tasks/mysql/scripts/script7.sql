
select et.emp_name, dt.dept_name, eit.salary 
from employee_table et inner join employee_info_table eit
on et.employee_info=eit.id inner join department_table dt
on et.dept_id=dt.dept_id inner join insurance_table it
on et.insurance_id=it.insurance_id
where (eit.DOJ>'2022-02-20' and eit.salary>20000) and (it.insurance_name='LIC' or  it.insurance_name='ICICI');
