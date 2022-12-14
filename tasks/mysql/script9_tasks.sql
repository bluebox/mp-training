select A.name, D.dept_name as department, ifnull(B.name, "NO BOSS!") as immediate_head, D.dept_name as Department
from Employee_Table A left join Employee_Table B 
on A.immediate_head_id = B.emp_id 
inner join Department_Table D 
on A.dept_id = D.dept_id
