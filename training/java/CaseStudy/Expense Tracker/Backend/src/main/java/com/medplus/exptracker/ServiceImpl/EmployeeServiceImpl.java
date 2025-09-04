package com.medplus.exptracker.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.exptracker.DTO.ExpenseDTO;
import com.medplus.exptracker.DTO.ExpenseForEmployeeDTO;
import com.medplus.exptracker.DTO.ExpensePerCategory;
import com.medplus.exptracker.DTO.UpdateExpenseDTO;
import com.medplus.exptracker.Dao.EmployeeDAO;
import com.medplus.exptracker.Dao.ExpenseDAO;
import com.medplus.exptracker.Exceptions.DBException;
import com.medplus.exptracker.Exceptions.MonthlyLimitException;
import com.medplus.exptracker.Model.Expense;
import com.medplus.exptracker.Model.User;
import com.medplus.exptracker.Service.EmployeeService;
import com.medplus.exptracker.Util.AuthUtil;


@Service
@EnableScheduling
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeDAO employeeDAO;
	@Autowired
	ExpenseDAO expenseDAO;
	@Autowired
	AuthUtil authUtil;
	

	
	@Override
    public void createExpense(ExpenseDTO expenseDto) throws MonthlyLimitException {		
		Expense expense = new Expense();
		BeanUtils.copyProperties(expenseDto, expense);
		
		User user= authUtil.getCurrentUser();
		expense.setEmployeeId(user.getId());
		expense.setManagerId(user.getManager_id());
		
        
        if (expense.getCategoryId() == 4) { 
            LocalDate expenseDate = expense.getDate();
            LocalDate today = LocalDate.now();
            
            //Dasu: Design decision , weather to allow next month dates as well or not , here allowing next month dates aswell
            if(today.getMonth() != expenseDate.getMonth())
            {
            	throw new RuntimeException("Cannot use different month than current one!");
            }
            
            employeeDAO.saveScheduledExpense(expense);
        } else {
        	
            if (isLimitExceededByCatByEmp(expense)) {
                employeeDAO.save(expense);
            } else {
                throw new MonthlyLimitException();
            }
        }
    }

	
	@Override
    public void updateExpense(UpdateExpenseDTO expenseDto) throws MonthlyLimitException,DBException{
    	int rowsAffected; 
    	
    	Expense expense = new Expense();
		BeanUtils.copyProperties(expenseDto, expense);
		
		User user= authUtil.getCurrentUser();
		expense.setEmployeeId(user.getId());		
		
        if(isLimitExceededByCatByEmp(expense)) {
        	rowsAffected = employeeDAO.update(expense);
        } else {
        	 throw new MonthlyLimitException();
        }
        if (rowsAffected == 0) {
            throw new DBException("Unable to update expense. It may not exist or is not in PENDING status.");
        }
    }

    @Override
    public void deleteExpense(Integer expenseId) throws DBException{
    	int userId = authUtil.getCurrentUser().getId();
        int rowsAffected = employeeDAO.delete(expenseId,userId);
        
        if (rowsAffected == 0) {
            throw new DBException("Unable to delete expense. It may not exist or is not in PENDING status.");
        }
    }
    
    @Override
    public List<ExpenseForEmployeeDTO> getExpensesByEmployeeId(Integer employeeId) {
        List<ExpenseForEmployeeDTO> expenses= employeeDAO.findByEmployeeId(employeeId);
        return expenses;
    }
    
    @Transactional
    @Override
	public boolean isLimitExceededByCatByEmp(Expense expense) {
		int employeeId = expense.getEmployeeId();
        int categoryId = expense.getCategoryId();
        int year = expense.getDate().getYear();
        int month = expense.getDate().getMonthValue();
        
        BigDecimal expensePerCategory = employeeDAO.getTotalExpenseByCategoryByEmployee(employeeId, categoryId, month,year);
        expensePerCategory = expensePerCategory.add(expense.getAmount());
        BigDecimal maxExpenseForCategory = expenseDAO.findMaxExpenseForCategory(categoryId);

		if(expensePerCategory.compareTo(maxExpenseForCategory) == -1) {
			return true;
		}
		else {
			return false;
		}
	}
    
    @Override
    public List<ExpensePerCategory> totalExpenseForCategories(int empId) {
    	return employeeDAO.getTotalExpenseForAllCategories(empId);
    }

}
