package com.medplus.exptracker.Dao;

import java.math.BigDecimal;
import java.util.List;

import com.medplus.exptracker.Model.Category;
import com.medplus.exptracker.Model.Expense;

public interface ExpenseDAO {
	Expense findById(Integer id);
	List<Category> findAllCategories();
	BigDecimal findMaxExpenseForCategory(int categoryId);
	List<Expense> findScheduledExpenses();
	void updateScheduledExpenseDate(Expense schExp);
}