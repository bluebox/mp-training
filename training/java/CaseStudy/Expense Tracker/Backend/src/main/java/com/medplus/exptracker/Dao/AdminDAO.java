package com.medplus.exptracker.Dao;

import java.math.BigDecimal;
import java.util.List;

import com.medplus.exptracker.DTO.EmployeeForAdminDTO;
import com.medplus.exptracker.DTO.ExpensePerCategory;
import com.medplus.exptracker.DTO.FilteredExpenseDTO;

public interface AdminDAO {
	List<FilteredExpenseDTO> fetchExpensesWithFilters(int managerId ,int  employeeId ,int categoryId,int monthValue);
	List<EmployeeForAdminDTO> fetchAllUsers();
	BigDecimal fetchExpenseForCurrentMonth();
	List<ExpensePerCategory> fetchExpensesForCategories();
}
