select et1.emp_name as employee, ifnull(et2.emp_name, 'Senior most employee in the current office') as immediate_head
from employee_table et1 left join employee_table et2
on et1.immediate_head_id=et2.emp_id inner join employee_office_table eot
on et2.emp_id=eot.emp_id inner join office_table ot
on eot.office_id=ot.office_id inner join employee_info_table eit1
on et1.employee_info=eit1.id inner join employee_info_table eit2
on et2.employee_info=eit2.id 
where ot.place='Madhapur' and eit1.salary<2*eit2.salary