package com.medplus.exptracker.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.exptracker.Dao.ExpenseDAO;
import com.medplus.exptracker.Model.Category;
import com.medplus.exptracker.Model.Expense;
import com.medplus.exptracker.Service.ExpenseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
    private ExpenseDAO expenseDAO;
	

    @Override
    public List<Category> getCategories() {
        return expenseDAO.findAllCategories();
    }


    @Override
    public Expense getExpenseById(Integer id) {
        return expenseDAO.findById(id);
    }
    
    @Override
    public List<Expense> getScheduledExpenses(){
    	return expenseDAO.findScheduledExpenses();
    }



	@Override
	public void updateScheduledExpenseDate(Expense schExp) {
		expenseDAO.updateScheduledExpenseDate(schExp);
	}
    
}