package com.rms.restaurant_management_system_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.dao.EmployeeDao;
import com.rms.restaurant_management_system_backend.domain.Employees;
import com.rms.restaurant_management_system_backend.service.EmployeeService;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeDao empDao;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employees employee) {
		Employees newEmployee = employeeService.addEmployee(employee);
		CustomResponse body = new CustomResponse(true, "member added successfully", newEmployee);
		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<?> viewAllEmployees() {
		List<Employees> emp = employeeService.getAllEmployees();
		CustomResponse body = new CustomResponse(true, "members fetched successfully", emp);
		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/active")
	public ResponseEntity<?> viewActiveEmployees() {
		List<Employees> emp = employeeService.getActiveEmployees();
		CustomResponse body = new CustomResponse(true, "members fetched successfully", emp);
		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
		Employees emp = empDao.getEmpById(id);
		CustomResponse body = new CustomResponse(true, "member fetched successfully", emp);
		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employees employee, @PathVariable int id) {
		employeeService.updateEmployee(employee, id);
		CustomResponse body = new CustomResponse(true, "member Updated successfully", null);
		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update-status/{id}")
	public ResponseEntity<?> updateEmployeeStatus(@RequestBody Employees employee, @PathVariable int id) {
		employeeService.updateEmpStatus(employee, id);
		CustomResponse body = new CustomResponse(true, "member Status Updated successfully", null);
		return new ResponseEntity<>(body, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployee(id);
		CustomResponse body = new CustomResponse(true, "member deleted successfully", null);
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

//	@PostMapping("/super")
//	public ResponseEntity<?> superEmployees(@RequestBody EmployeeSearchCriteria empSearchCriteria) {
//		List<Employees> emp = empDao.getSuperEmployees(empSearchCriteria);
//		CustomResponse body = new CustomResponse(true, "members fetched successfully", emp);
//		return new ResponseEntity<>(body, HttpStatus.OK);
//
//	}

}