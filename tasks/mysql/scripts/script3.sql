select ot.place, count(ot.place) as 'No. of employees'
from employee_table et inner join employee_office_table eot
on et.emp_id=eot.emp_id inner join office_table ot
on eot.office_id=ot.office_id group by ot.place;
