package com.medplus.exptracker.DaoImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medplus.exptracker.DTO.EmployeeForAdminDTO;
import com.medplus.exptracker.DTO.ExpensePerCategory;
import com.medplus.exptracker.DTO.FilteredExpenseDTO;
import com.medplus.exptracker.Dao.AdminDAO;
import com.medplus.exptracker.Model.Expense;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdminDAOImpl implements AdminDAO {
    
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<FilteredExpenseDTO> fetchExpensesWithFilters(int managerId, int employeeId, int categoryId, int monthValue) {
        String query = """
	            SELECT e.*, u.username as employeeName, c.name as categoryName 
	            FROM expenses e
	            JOIN users u ON e.employee_id = u.id
	            JOIN categories c ON e.category_id = c.id
	            WHERE (? = 0 OR e.manager_id = ?)
	            AND (? = 0 OR e.employee_id = ?)
	            AND (? = 0 OR e.category_id = ?)
	            AND (? = 0 OR MONTH(e.date) = ?) 
	            """;
            
        return jdbcTemplate.query(query, 
            new BeanPropertyRowMapper<>(FilteredExpenseDTO.class),
            managerId, managerId,
            employeeId, employeeId,
            categoryId, categoryId,
            monthValue, monthValue
        );
    }
    
    @Override
    public List<EmployeeForAdminDTO> fetchAllUsers() {
		String getEmployees= "SELECT users.username,users.id,users.manager_id FROM users WHERE role_id  = 3";
		return jdbcTemplate.query(getEmployees,new BeanPropertyRowMapper<>(EmployeeForAdminDTO.class));
    }
    
    @Override
    public BigDecimal fetchExpenseForCurrentMonth() {
    	String getEmployees= "SELECT SUM(expenses.amount) FROM expenses "
    						+ "WHERE status='APPROVED' AND MONTH(date) = MONTH(CURRENT_DATE)";
		return jdbcTemplate.queryForObject(getEmployees,BigDecimal.class);
    }

	@Override
	public List<ExpensePerCategory> fetchExpensesForCategories() {
		String getEmployees= "SELECT SUM(expenses.amount) as totalExpense ,categories.name as name "
				+ "FROM expenses LEFT JOIN categories ON categories.id = expenses.category_id "
				+ "WHERE status='APPROVED' AND MONTH(date) = MONTH(CURRENT_DATE) "
				+ "GROUP BY categories.id";
		return jdbcTemplate.query(getEmployees,new BeanPropertyRowMapper<>(ExpensePerCategory.class));
	}
}
