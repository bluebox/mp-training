package com.medplus.exptracker.Dao;

import java.math.BigDecimal;
import java.util.List;

import com.medplus.exptracker.DTO.ExpenseForEmployeeDTO;
import com.medplus.exptracker.DTO.ExpensePerCategory;
import com.medplus.exptracker.Model.Expense;

public interface EmployeeDAO {
	public List<ExpenseForEmployeeDTO> findByEmployeeId(Integer employeeId);
	 public void save(Expense expense);
	 int update(Expense expense);
	 int delete(Integer expenseId, Integer employeeId);
	 
	 public BigDecimal getTotalExpenseByCategoryByEmployee(int employeeId, int categoryId,int month, int year);
	 List<ExpensePerCategory> getTotalExpenseForAllCategories(int empId);
	 void saveScheduledExpense(Expense scheduledExpense);
}
