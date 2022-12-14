select sum(eit.salary) as sum
from employee_table et inner join employee_info_table eit
on et.employee_info=eit.id 
where DOJ>'2022-01-02';
