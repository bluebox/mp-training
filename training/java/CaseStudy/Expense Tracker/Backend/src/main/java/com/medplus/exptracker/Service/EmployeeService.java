package com.medplus.exptracker.Service;

import java.util.List;

import com.medplus.exptracker.DTO.ExpenseDTO;
import com.medplus.exptracker.DTO.ExpenseForEmployeeDTO;
import com.medplus.exptracker.DTO.ExpensePerCategory;
import com.medplus.exptracker.DTO.UpdateExpenseDTO;
import com.medplus.exptracker.Exceptions.DBException;
import com.medplus.exptracker.Exceptions.MonthlyLimitException;
import com.medplus.exptracker.Model.Expense;

public interface EmployeeService {
	void createExpense(ExpenseDTO expense) throws MonthlyLimitException;
	void updateExpense(UpdateExpenseDTO expense) throws MonthlyLimitException, DBException;
	public void deleteExpense(Integer expenseId) throws DBException;
	public List<ExpenseForEmployeeDTO> getExpensesByEmployeeId(Integer employeeId);
	public boolean isLimitExceededByCatByEmp(Expense expense);
	
	List<ExpensePerCategory> totalExpenseForCategories(int empId);
}
