Select et1.name from Employee_Table et1 inner join Employee_Table et2 
on et1.immediate_head_id=et2.emp_id inner join Employee_Office_Table eo
on et1.immediate_head_id = eo.emp_id inner join Office_Table ot
on eo.office_id = ot.office_id inner join Employee_Info_Table ei1 
on et1.employee_info = ei1.id inner join Employee_Info_Table ei2
on et2.employee_info = ei2.id 
where ot.place = 'BANGALORE' and  ei1.Salary * 1.6 < ei2.Salary