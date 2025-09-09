package com.medplus.exptracker.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.medplus.exptracker.DTO.ManagerExpenseDTO;
import com.medplus.exptracker.DTO.ManagerForAdminDTO;
import com.medplus.exptracker.Dao.ManagerDAO;
import com.medplus.exptracker.Exceptions.MonthlyLimitException;
import com.medplus.exptracker.Model.Expense;
import com.medplus.exptracker.Model.User;
import com.medplus.exptracker.Service.EmployeeService;
import com.medplus.exptracker.Service.ExpenseService;
import com.medplus.exptracker.Service.ManagerService;
import com.medplus.exptracker.Service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ManagerDAO managerDAO;

    @Autowired
    private UserService userService;

    @Override
    public List<ManagerExpenseDTO> getExpensesByManagerId(Integer managerId) {
        return managerDAO.findExpensesByManagerId(managerId);
    }

    @Override
    public void approveExpense(Integer id, String remarks) throws MonthlyLimitException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserByUserName(username);
        Integer managerId = user.getId();

        Expense expense = new Expense();
        expense.setStatus("APPROVED");
        expense.setManagerId(managerId);
        expense.setId(id);
        expense.setRemarks(remarks);

        int rowsAffected;
        Expense expenseToApprove = expenseService.getExpenseById(id)  ;
        log.info("Trying to approve this expense: " + expenseToApprove);
        if (employeeService.isLimitExceededByCatByEmp(expenseToApprove)) {
            rowsAffected = managerDAO.updateStatus(expense);
        } else {
            throw new MonthlyLimitException("Unable to approve expense. The limit for the category for the employee has exceeded!.");
        }
        if (rowsAffected == 0) {
            throw new MonthlyLimitException("Unable to approve expense. It may not exist or is not in PENDING status.");
        }
    }

    @Override
    public void rejectExpense(Integer id, String remarks) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserByUserName(username);
        Integer managerId = user.getId();

        if (remarks == null || remarks.trim().isEmpty()) {
            throw new RuntimeException("Remark cannot be empty for rejecting an expense");
        }
        
        Expense expense = new Expense();
        expense.setStatus("REJECTED");
        expense.setManagerId(managerId);
        expense.setId(id);
        expense.setRemarks(remarks);

        int rowsAffected = managerDAO.updateStatus(expense);
        if (rowsAffected == 0) {
            throw new RuntimeException("Unable to reject expense. It may not exist or is not in PENDING status.");
        }
    }

	@Override
	public List<ManagerForAdminDTO> getAllManager() {
		List<ManagerForAdminDTO> managers;
		managers = managerDAO.fetchAllManagers();
 		return managers;
	}
}
