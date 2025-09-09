package com.medplus.exptracker.schedulers;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.medplus.exptracker.Dao.EmployeeDAO;
import com.medplus.exptracker.Model.Expense;
import com.medplus.exptracker.Service.ExpenseService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExpenseScheduler {
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	EmployeeDAO empDao;
	
	
	//0 0 0 1 * *
	@Scheduled(cron = "0 0 0 1 * *")
	public void addScheduledExpense() {
		//take all the schduledExpenses (getScheduledExpenses)
		List<Expense> scheduledExpenses = expenseService.getScheduledExpenses();
		
		//add them to expenses table if they are before today ( addExpense )
		
		LocalDate today = LocalDate.now();
		for(Expense schExpense : scheduledExpenses) {
			if(today.isAfter(schExpense.getDate())) {
				empDao.save(schExpense);
				//update all the added dates of the scheduledExpenses to next month ( updateScheduledExpense )
				expenseService.updateScheduledExpenseDate(schExpense); 
			}
		}
	}
	
	
	@Scheduled(cron = "0 53 15 * * *")
	public void test() {
		log.info("Hi PReNDSSS");
	}
}

