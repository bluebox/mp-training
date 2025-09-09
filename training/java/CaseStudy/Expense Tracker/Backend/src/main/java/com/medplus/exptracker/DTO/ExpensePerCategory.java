package com.medplus.exptracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpensePerCategory {
	String name;
	Integer totalExpense;
}
