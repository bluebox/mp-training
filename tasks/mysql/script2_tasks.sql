select dt.dept_name 
from Department_Table dt inner join Employee_Table et 
on dt.dept_id = et.dept_id inner join Employee_Office_Table eo
on et.emp_id = eo.emp_id inner join Office_Table ot
on eo.office_id = ot.office_id where place = 'HI-TECH';