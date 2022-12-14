select distinct et.emp_name, it.insurance_name, eit.salary
from employee_table et inner join employee_info_table eit 
on et.employee_info=eit.id inner join insurance_table it 
on et.insurance_id=it.insurance_id group by eit.salary order by eit.salary desc limit 2,1;
