select et.name, dt.dept_name, ei.Salary from Employee_Table et
left join Employee_Info_Table ei
on et.employee_info = ei.id inner join Employee_Office_Table eo
on et.emp_id = eo.emp_id inner join Office_Table ot
on eo.office_id = ot.office_id inner join Insurance_Table it
on et.insurance_id = it.insurance_id inner join Department_Table dt
on et.dept_id = dt.dept_id
where ei.Salary > 20000 and (it.insurance_name like 'LIC%' OR it.insurance_name like 'HDFC%')
and ot.place = 'MADHAPUR' and ei.DOJ > '2022-02-20'