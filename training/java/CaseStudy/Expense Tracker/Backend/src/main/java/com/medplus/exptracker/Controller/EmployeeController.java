package com.medplus.exptracker.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.exptracker.DTO.ExpenseForEmployeeDTO;
import com.medplus.exptracker.DTO.ExpensePerCategory;
import com.medplus.exptracker.DTO.UpdateExpenseDTO;
import com.medplus.exptracker.DTO.ExpenseDTO;
import com.medplus.exptracker.Exceptions.DBException;
import com.medplus.exptracker.Exceptions.MonthlyLimitException;
import com.medplus.exptracker.Model.User;
import com.medplus.exptracker.Service.EmployeeService;
import com.medplus.exptracker.Service.ExpenseService;
import com.medplus.exptracker.Service.UserService;
import com.medplus.exptracker.Util.AuthUtil;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = "*",allowCredentials = "true")
public class EmployeeController {
	
	@Autowired
	ExpenseService expenseService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	UserService userService;
	
	@Autowired
	AuthUtil authUtil;
	
	@PostMapping("/addexpense")
    public ResponseEntity<Map<String,String>> createExpense(@Valid @RequestBody ExpenseDTO expense) throws MonthlyLimitException {
        log.info("Creating expense: " + expense);
        
        LocalDate today = LocalDate.now();
        if(expense.getCategoryId() != 4) {
        	if(today.isBefore(expense.getDate())) {
            	throw new RuntimeException("Date cannot be in the future");
            }
        }
        
        employeeService.createExpense(expense);
        
        var res = new HashMap<String,String>();
        res.put("message", "Expense Created Succesfully!");
        return ResponseEntity.ok(res);
    }
	
	@GetMapping("/get-expenses")
    public ResponseEntity<List<ExpenseForEmployeeDTO>> getExpensesForEmployee() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentuser = userService.getUserByUserName(auth.getName());
		
		List<ExpenseForEmployeeDTO> expenses = employeeService.getExpensesByEmployeeId(currentuser.getId());
        return ResponseEntity.ok(expenses);
    }

	@PutMapping("/update-expense")
    public ResponseEntity<Map<String,String>> updateExpense(@Valid @RequestBody UpdateExpenseDTO expense) throws MonthlyLimitException, DBException {
		employeeService.updateExpense(expense);
		
		int empId = authUtil.getCurrentUser().getId();
        var res = new HashMap<String,String>();
        res.put("message", "Expense Updated Succesfully!");
        return ResponseEntity.ok(res);
    }
	
	@DeleteMapping("/delete-expense/{expenseId}")
	public ResponseEntity<Map<String,String>> deleteExpense(@PathVariable int expenseId) throws DBException{		
		var res = new HashMap<String,String>();
		employeeService.deleteExpense(expenseId);
		
		res.put("message", "Succesfully deleted expense");
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/get-expense-per-category")
	public List<ExpensePerCategory> getTotalExpenseForCategories(){
		List<ExpensePerCategory> res;
		int empId = authUtil.getCurrentUser().getId();
		log.info("Got employee id to get per category amonuts : " +empId);
		res = employeeService.totalExpenseForCategories(empId);
		
		return res;
	}
	
}
