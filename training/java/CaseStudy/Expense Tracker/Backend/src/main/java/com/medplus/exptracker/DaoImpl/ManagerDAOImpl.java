package com.medplus.exptracker.DaoImpl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medplus.exptracker.DTO.ManagerExpenseDTO;
import com.medplus.exptracker.DTO.ManagerForAdminDTO;
import com.medplus.exptracker.Dao.ManagerDAO;
import com.medplus.exptracker.Model.Expense;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ManagerDAOImpl implements ManagerDAO{
	
	private final JdbcTemplate jdbcTemplate;
	
	@Override
    public List<ManagerExpenseDTO> findExpensesByManagerId(Integer managerId) {
        String findByManager = "SELECT id, employee_id, category_id, amount, description, date, status, remarks, receipt_url FROM expenses WHERE manager_id = ?";
        return jdbcTemplate.query(findByManager, new BeanPropertyRowMapper<>(ManagerExpenseDTO.class), managerId);
    }
	
    @Override
    public int updateStatus(Expense expense) {
        String updatests = "UPDATE expenses SET status=?, remarks=? WHERE id=? AND manager_id=? AND status='PENDING'";
        return jdbcTemplate.update(updatests, expense.getStatus(), expense.getRemarks(), expense.getId(), expense.getManagerId());
    }

	@Override
	public List<ManagerForAdminDTO> fetchAllManagers() {
		String fetchAllManagers = "SELECT id, username FROM users WHERE role_id=?";
		return jdbcTemplate.query(fetchAllManagers,new BeanPropertyRowMapper<>(ManagerForAdminDTO.class),2);
	}

	
}
