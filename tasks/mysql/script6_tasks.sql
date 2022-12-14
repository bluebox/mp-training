select sum(ei.Salary) as SUM_SALARY from Employee_Info_Table ei 
inner join Employee_Table et
on ei.id = et.employee_info inner join Employee_Office_Table eo
on et.emp_id = eo.emp_id inner join Office_Table ot
on eo.office_id = ot.office_id where place = 'MADHAPUR' and DOJ>'2022-01-02' ; 