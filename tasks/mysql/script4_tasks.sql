select et.name, it.insurance_name, ei.Salary from Employee_Table et
inner join Insurance_Table it 
on et.insurance_id = it.insurance_id inner join Employee_Info_Table ei
on et.employee_info = ei.id 
where Salary = (select Salary from Employee_Info_Table order by Salary desc limit 2,1);  
