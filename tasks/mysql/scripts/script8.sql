select et1.emp_name as employee, ifnull(et2.emp_name, 'Senior most employee in the current office') as immediate_head
from employee_table et1 left join employee_table et2
on et1.immediate_head_id=et2.emp_id 