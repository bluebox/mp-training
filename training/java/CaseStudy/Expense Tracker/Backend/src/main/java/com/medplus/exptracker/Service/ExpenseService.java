package com.medplus.exptracker.Service;

import java.util.List;

import com.medplus.exptracker.Model.Category;
import com.medplus.exptracker.Model.Expense;

public interface ExpenseService {
    Expense getExpenseById(Integer id);
    List<Category> getCategories();
	List<Expense> getScheduledExpenses();   
	void updateScheduledExpenseDate(Expense schExp);
}