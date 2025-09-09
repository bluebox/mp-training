package com.medplus.exptracker.DaoImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medplus.exptracker.Dao.ExpenseDAO;
import com.medplus.exptracker.Model.Category;
import com.medplus.exptracker.Model.Expense;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExpenseDAOImpl implements ExpenseDAO {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Expense findById(Integer id) {
        String findexpid = "SELECT * FROM expenses WHERE id = ?";
        return jdbcTemplate.queryForObject(findexpid, new BeanPropertyRowMapper<>(Expense.class), id);
    }
    
    @Override
    public List<Category> findAllCategories() {
        String getcate = "SELECT * FROM categories";
        return jdbcTemplate.query(getcate, new BeanPropertyRowMapper<>(Category.class));
    }
    
    @Override
    public BigDecimal findMaxExpenseForCategory(int categoryId) {
    	String getMaxForCategory = "SELECT monthly_limit FROM categories WHERE id = ?";
    	return jdbcTemplate.queryForObject(getMaxForCategory, BigDecimal.class,categoryId);
    }

	@Override
	public List<Expense> findScheduledExpenses() {
		String getAllSchExpenses = """
				SELECT 
					id,
					employee_id as employeeId,
					category_id as categoryId,
					amount,
					description,
					date,
					status,
					manager_id as managerId,
					remarks,
					receipt_url as receiptUrl
				FROM scheduled_expenses;
				""";
		
		return jdbcTemplate.query(getAllSchExpenses, new BeanPropertyRowMapper<>(Expense.class));
	}

	@Override
	public void updateScheduledExpenseDate(Expense schExp) {
		String updateDateOfSchExpense = """
				UPDATE scheduled_expenses SET date=?
				""";
		
		LocalDate nextDate = schExp.getDate().plusMonths(1);
		
		int effectedRows = jdbcTemplate.update(updateDateOfSchExpense,nextDate);
		
		if(effectedRows == 0 ) {
			throw new RuntimeException("Could not update the date of Scheduled Expense");
		}
	}
    
}