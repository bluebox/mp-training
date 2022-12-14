select A.name, B.name as immediate_head from Employee_Table A, Employee_Table B
where A.immediate_head_id = B.emp_id