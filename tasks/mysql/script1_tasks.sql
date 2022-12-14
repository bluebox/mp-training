select  et.name, dt.dept_name, it.insurance_name 
from Employee_Table et inner join Department_Table dt 
on et.dept_id = dt.dept_id inner join Insurance_Table it
on et.insurance_id = it.insurance_id;