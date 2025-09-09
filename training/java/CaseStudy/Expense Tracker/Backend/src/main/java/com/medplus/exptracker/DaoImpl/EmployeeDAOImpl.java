package com.medplus.exptracker.DaoImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.medplus.exptracker.DTO.ExpenseForEmployeeDTO;
import com.medplus.exptracker.DTO.ExpensePerCategory;
import com.medplus.exptracker.Dao.EmployeeDAO;
import com.medplus.exptracker.Model.Expense;
import com.medplus.exptracker.Util.AuthUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmployeeDAOImpl implements EmployeeDAO{
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private AuthUtil authUtil;
	@Override
    public List<ExpenseForEmployeeDTO> findByEmployeeId(Integer employeeId) {
        String findemp = "SELECT e.id,c.id as categoryId,c.name as categoryName , e.date,e.amount,e.description,e.remarks,e.status,e.receipt_url as receiptUrl FROM expenses e "
        				+ "LEFT JOIN categories c on e.category_id = c.id " 
        				+ "WHERE employee_id = ?";
        
        return jdbcTemplate.query(findemp, new BeanPropertyRowMapper<>(ExpenseForEmployeeDTO.class), employeeId);
    }
	
	@Override
    public void save(Expense expense) {
        String save = "INSERT INTO expenses (employee_id, category_id, amount, description, date, status, manager_id, remarks, receipt_url) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        log.info("Trying to add expense : "+ expense);
        jdbcTemplate.update(save,
                expense.getEmployeeId(),
                expense.getCategoryId(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getDate(),
                expense.getStatus() != null ? expense.getStatus() : "PENDING",
                expense.getManagerId(),
                expense.getRemarks(),
                expense.getReceiptUrl());
    }
	
	@Override
    public int update(Expense expense) {
        String update = "UPDATE expenses SET category_id=?, amount=?, description=?, date=?, receipt_url=? " +
                "WHERE id=? AND employee_id=? AND status='PENDING'";
        
       return jdbcTemplate.update(update,
                expense.getCategoryId(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getDate(),
                expense.getReceiptUrl(),
                expense.getId(),
                expense.getEmployeeId());
    }
	
	@Override
    public int delete(Integer expenseId,Integer employeeId) {
        String delete = "DELETE FROM expenses WHERE id = ? and employee_id = ? AND status = 'PENDING'";
        return jdbcTemplate.update(delete, expenseId,employeeId);
    }
	
	@Override
	public BigDecimal getTotalExpenseByCategoryByEmployee(int employeeId, int categoryId,int month, int year) {
		String expforcatbymon = "SELECT SUM(amount) FROM expenses " +
                "WHERE employee_id = ? AND category_id = ? " +
                "AND MONTH(date) = ? AND YEAR(date) = ? AND status = 'APPROVED'";
		
		BigDecimal result =  jdbcTemplate.queryForObject(expforcatbymon, BigDecimal.class, employeeId, categoryId, month, year);
		if(result == null) {
			result = new BigDecimal("0");
        }
		return result;
	}

	
	@Override
	public List<ExpensePerCategory> getTotalExpenseForAllCategories(int empId){
		String TotalExpensesForAllCategories= 
				"SELECT c.name as name, COALESCE(SUM(e.amount), 0) as totalExpense " +
		        "from categories c " +
		        "LEFT JOIN expenses e ON c.id = e.category_id " +
		        "AND MONTH(e.date) = MONTH(CURRENT_DATE) " +
		        "AND e.employee_id = ? " +
		        "AND e.status = 'APPROVED' " +
		        "GROUP BY c.id, c.name";
		
		List<ExpensePerCategory> result = jdbcTemplate.query(TotalExpensesForAllCategories,new BeanPropertyRowMapper<>(ExpensePerCategory.class),empId);
		return result;
	}
	
	@Override
	public void saveScheduledExpense(Expense scheduledExpense) {
		String SaveScheduledExpense = """
				INSERT INTO scheduled_expenses(
								employee_id,
								category_id,
								amount,
								description,
								date,
								manager_id,
								remarks,
								receipt_url) 
				VALUES(
						:employeeId,
						:categoryId,
						:amount,
						:description,
						:date,
						:managerId,
						:remarks, 
						:receiptUrl
					)
				""";
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(scheduledExpense);
		int rowsEffected = namedJdbcTemplate.update(SaveScheduledExpense,parameters);
		
		if(rowsEffected == 0) {
			throw new RuntimeException("Could not add scheduled Expense....");
		}
	}
}
