package com.medplus.exptracker.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.exptracker.DTO.ExpenseDTO;
import com.medplus.exptracker.DTO.ManagerExpenseDTO;
import com.medplus.exptracker.DTO.UserDTO;
import com.medplus.exptracker.Exceptions.MonthlyLimitException;
import com.medplus.exptracker.Model.Category;
import com.medplus.exptracker.Model.User;
import com.medplus.exptracker.Service.ExpenseService;
import com.medplus.exptracker.Service.ManagerService;
import com.medplus.exptracker.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/manager/expenses")
@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = "*",allowCredentials = "true")
public class ManagerExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;

    private Map<Integer, String> categoryIdToNameMap;

    @Autowired
    public void initCategoryMap() {
        categoryIdToNameMap = expenseService.getCategories().stream()
                .collect(Collectors.toMap(Category::getId, Category::getName));
    }
// replace with util function
    //add back end filter
    @GetMapping
    public ResponseEntity<List<ManagerExpenseDTO>> getTeamExpenses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserByUserName(username);
        Integer managerId = user.getId();

        List<ManagerExpenseDTO> expenses = managerService.getExpensesByManagerId(managerId);
        List<ManagerExpenseDTO> enrichedExpenses = expenses.stream().map(expense -> {
            var emp = userService.getUserById(expense.getEmployeeId());
            if (emp != null) {
                expense.setEmployeeName(emp.getUsername());
            }

            Integer catId = expense.getCategoryId();
            if (catId != null && categoryIdToNameMap != null) {
                expense.setCategoryName(categoryIdToNameMap.getOrDefault(catId, "Unknown"));
            }

            return expense;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(enrichedExpenses);
    }
    

    @PutMapping("/{id}/approve")
    public ResponseEntity<Map<String, String>> approveExpense(@PathVariable Integer id, @RequestBody ExpenseDTO expense) throws MonthlyLimitException {
        managerService.approveExpense(id, expense.getRemarks());
        var res = new HashMap<String, String>();
        res.put("message", "Expense approved successfully!");
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Map<String, String>> rejectExpense(@PathVariable Integer id, @RequestBody ExpenseDTO expense) {
        managerService.rejectExpense(id, expense.getRemarks());
        var res = new HashMap<String, String>();
        res.put("message", "Expense rejected successfully!");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/approvedAmounts")
    public ResponseEntity<List<ManagerExpenseDTO>> getApprovedAmounts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserByUserName(username);
        Integer managerId = user.getId();
        List<ManagerExpenseDTO> approvedExpenses = managerService.getExpensesByManagerId(managerId).stream()
                .filter(exp -> "APPROVED".equals(exp.getStatus()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(approvedExpenses);
    }

    @GetMapping("/categoryWiseApproved")
    public ResponseEntity<List<Map<String, Object>>> getCategoryWiseApproved() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserByUserName(username);
        Integer managerId = user.getId();

        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        List<ManagerExpenseDTO> currentMonthApproved = managerService.getExpensesByManagerId(managerId).stream()
                .filter(exp -> "APPROVED".equals(exp.getStatus()))
                .filter(exp -> {
                    LocalDate expenseDate = exp.getDate();
                    return expenseDate != null &&
                            expenseDate.getMonthValue() == currentMonth &&
                            expenseDate.getYear() == currentYear;
                })
                .collect(Collectors.toList());

        //change the object to double
        
        List<Category> categories = expenseService.getCategories();
        Map<Integer, Double> categoryAmounts = currentMonthApproved.stream()
                .collect(Collectors.groupingBy(
                		ManagerExpenseDTO::getCategoryId,
                        Collectors.summingDouble(expense -> expense.getAmount().doubleValue())
                ));

        List<Map<String, Object>> result = categories.stream()
                .map(category -> {
                    Integer categoryId = category.getId();
                    String categoryName = category.getName();
                    Double approvedAmount = categoryAmounts.getOrDefault(categoryId, 0.0);

                    Map<String, Object> categoryData = new HashMap<>();
                    categoryData.put("categoryName", categoryName);
                    categoryData.put("approvedAmount", approvedAmount);

                    return categoryData;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees")
    public ResponseEntity<Map<String, Object>> getEmployeesUnderManager() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User manager = userService.getUserByUserName(username);
        Integer managerId = manager.getId();
        List<UserDTO> employees = userService.getEmployeesByManagerId(managerId);

        List<String> employeeUsernames = employees.stream()
            .map(UserDTO::getUsername)
            .distinct()
            .sorted()
            .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("employees", employeeUsernames);
        response.put("totalCount", employeeUsernames.size());
        return ResponseEntity.ok(response);
    }

}