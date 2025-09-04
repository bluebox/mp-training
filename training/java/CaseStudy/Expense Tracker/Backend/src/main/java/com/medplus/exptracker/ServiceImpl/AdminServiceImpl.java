package com.medplus.exptracker.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.exptracker.DTO.EmployeeForAdminDTO;
import com.medplus.exptracker.DTO.ExpensePerCategory;
import com.medplus.exptracker.DTO.FilteredExpenseDTO;
import com.medplus.exptracker.Dao.AdminDAO;
import com.medplus.exptracker.Model.Expense;
import com.medplus.exptracker.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public List<FilteredExpenseDTO> getExpensesWithFilters(int managerId, int employeeId, int categoryId, int monthValue) {
		
		return adminDAO.fetchExpensesWithFilters(managerId, employeeId, categoryId, monthValue);
		
	}
	
	@Override
	public List<EmployeeForAdminDTO> getAllEmployees(){
		return adminDAO.fetchAllUsers();
	}
	
	@Override
	public BigDecimal getExpenseForCurrentMonth() {
		return adminDAO.fetchExpenseForCurrentMonth();
	}
	
	@Override
	public List<ExpensePerCategory> getExpenseForCategories() {
		return adminDAO.fetchExpensesForCategories();
	}
}
