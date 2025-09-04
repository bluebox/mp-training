package com.medplus.exptracker.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.exptracker.Service.AdminService;
import com.medplus.exptracker.Service.ManagerService;
import com.medplus.exptracker.Service.UserService;

import com.medplus.exptracker.DTO.EmployeeForAdminDTO;
import com.medplus.exptracker.DTO.ExpensePerCategory;
import com.medplus.exptracker.DTO.FilteredExpenseDTO;
import com.medplus.exptracker.DTO.ManagerForAdminDTO;
import com.medplus.exptracker.DTO.RegisterUserDTO;
import com.medplus.exptracker.Model.User;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = "*",allowCredentials = "true")
public class AdminController {

	@Autowired
	UserService userService;
	@Autowired
	ManagerService managerService;
	@Autowired
	AdminService adminService;
		
	@PostMapping("/add-user")
	public ResponseEntity<Map<String,String>> addNewUser(@RequestBody RegisterUserDTO user){
		

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(user.getRole_id()== 1) {
			throw new RuntimeException("YOU CANNOT CREATE AN ADMIN!");
		}
		else if(user.getRole_id() == 2) {
			user.setManager_id(null);
		} else if(user.getManager_id() == 0 || user.getManager_id() == null) {
			throw new RuntimeException("You need to assign a manager to the employee");
		}
		
		//Dasu: change to pojo in service
		User registerUser = new User();
		BeanUtils.copyProperties(user, registerUser);
		
		userService.addUser(registerUser);
		var res = new HashMap<String,String>();
        res.put("message", "User Created Succesfully!");
        return ResponseEntity.ok(res);
	}
	
	@GetMapping("/get-managers")
    public List<ManagerForAdminDTO> getAllManagers() {
    	var result = managerService.getAllManager();
        return result;
    }
	
	@GetMapping("/expenses")
	public List<FilteredExpenseDTO> getExpenses(
            @RequestParam(defaultValue = "0") int employeeId,
            @RequestParam(defaultValue = "0") int managerId,
            @RequestParam(defaultValue = "0") int categoryId,
            @RequestParam(defaultValue = "0") int month) {
	 
        return adminService.getExpensesWithFilters(managerId, employeeId, categoryId, month);
    }
	
	
	
	@GetMapping("/employees")
	public List<EmployeeForAdminDTO> getEmployees(){
		return adminService.getAllEmployees();
	}
	
	@GetMapping("/get-total-expense")
	public ResponseEntity<Map<String,BigDecimal>> getTotalExpenseForCurrentMonth(){
		var res = new HashMap<String,BigDecimal>();
		res.put("totalExpense",adminService.getExpenseForCurrentMonth());
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/get-expense-per-category")
	public List<ExpensePerCategory> getTotalExpensePerCategoryForCurrentMonth(){		
		return adminService.getExpenseForCategories();
	}
	
	
}
