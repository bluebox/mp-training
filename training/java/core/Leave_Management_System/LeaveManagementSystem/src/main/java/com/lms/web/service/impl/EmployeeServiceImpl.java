package com.lms.web.service.impl;

import com.lms.web.dao.impl.EmployeeDAOImpl;
import com.lms.web.exceptions.ValidationException;
import com.lms.web.model.Employee;
import com.lms.web.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeDAOImpl employeeDAO;

    @Override
    public Employee getEmployeeByEmail(String email) {
    	if(email==null) {
			throw new ValidationException("Email cannot be null");
		}
        return employeeDAO.findByEmail(email);
    }
}