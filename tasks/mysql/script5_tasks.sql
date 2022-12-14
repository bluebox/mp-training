select dt.dept_name, avg(ei.Salary) as avg
from Department_Table dt inner join Employee_Table et 
on dt.dept_id = et.dept_id inner join Employee_Office_Table eo
on et.emp_id = eo.emp_id left join Employee_Info_Table ei
on et.employee_info = ei.id inner join Office_Table ot
on eo.office_id = ot.office_id where place = 'HI-TECH' group by dt.dept_name;