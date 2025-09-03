//package com.rms.restaurant_management_system_backend.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.rms.restaurant_management_system_backend.dao.EmployeeDao;
//import com.rms.restaurant_management_system_backend.domain.Employees;
//import com.rms.restaurant_management_system_backend.service.EmployeeService;
//import com.rms.restaurant_management_system_backend.service.EmployeesClientService;
//import com.rms.restaurant_management_system_backend.utilities.CustomResponse;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.Valid;
//
//@CrossOrigin("http://localhost:5173")
//@RestController
//@RequestMapping("/api/employees")
//
//public class EmployeeClientController {
//
//	private final CustomResponse customResponse;
//
//	@Autowired
//	EmployeeService employeeService;
//
//	@Autowired
//	EmployeeDao empDao;
//
//	@Autowired
//	EmployeesClientService employeesClientService;
//
//	EmployeeClientController(CustomResponse customResponse) {
//		this.customResponse = customResponse;
//	}
//
//	@PreAuthorize("hasRole('ADMIN')")
//	@PostMapping("/add")
//	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employees employee, HttpServletRequest request) {
//		String tokString = request.getHeader("Authorization").substring(7);
//		CustomResponse body = employeesClientService.addEmployee(tokString, employee);
//		return new ResponseEntity<>(body, HttpStatus.OK);
//
//	}
//
//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping
//	public ResponseEntity<?> viewAllEmployees(HttpServletRequest request) {
//
//		String tokenString = request.getHeader("Authorization").substring(7);
//		CustomResponse body = employeesClientService.getEmployees(tokenString);
//		return new ResponseEntity<>(body, HttpStatus.OK);
//
//	}
//
//	@PreAuthorize("hasRole('ADMIN')")
//	@PutMapping("/update/{id}")
//	public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employees employee, @PathVariable int id,
//			HttpServletRequest request) {
//		String tokenString = request.getHeader("Authorization").substring(7);
//		CustomResponse body = employeesClientService.updateEmployee(tokenString, id, employee);
//		return new ResponseEntity<>(body, HttpStatus.OK);
//
//	}
//
//	@PreAuthorize("hasRole('ADMIN')")
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteEmployee(@PathVariable int id, HttpServletRequest request) {
//		String tokenString = request.getHeader("Authorization").substring(7);
//		CustomResponse body = employeesClientService.deleteEmployee(tokenString, id);
//		return new ResponseEntity<>(body, HttpStatus.OK);
//	}
//
//}
