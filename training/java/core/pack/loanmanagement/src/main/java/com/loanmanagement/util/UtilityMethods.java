package com.loanmanagement.util;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class UtilityMethods {

	
	public static double calculateEMI(double principal, double annualRate, int tenureInDays) {
	    double timeInYears = tenureInDays / 365.0;
	    double simpleInterest = (principal * annualRate * timeInYears) / 100;
	    double totalPayable = principal + simpleInterest;
	    int days = (int) Math.ceil(tenureInDays);
	    return Math.round((totalPayable / days) * 100.0) / 100.0; 
	}
	
	public static double totalPayement(double principal, double annualRate, int tenureInDays) {
		 double timeInYears = tenureInDays / 365.0;
		    double simpleInterest = (principal * annualRate * timeInYears) / 100;
		    double totalPayable = principal + simpleInterest;
		    return totalPayable;
	}
	
	public static Date calculateDueDate(Date startDate, int tenureInDays) {
        LocalDate localStartDate = startDate.toLocalDate();
        LocalDate dueDate = localStartDate.plusDays(tenureInDays);
        return Date.valueOf(dueDate);
    }
	
	public static int getCreditScore() {
		 Random random = new Random();
	     return 600 + random.nextInt(301); 
	}
	
	

}
