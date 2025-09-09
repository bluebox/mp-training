package com.medplus.exptracker.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilteredExpenseDTO {
	String employeeName;
	String categoryName;
	
	Integer amount;
	LocalDate date;
	String status;
	String description;
	Integer managerId;
	
}
