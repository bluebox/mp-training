package com.medplus.exptracker.Exceptions;

public class MonthlyLimitException extends Exception{
	public MonthlyLimitException() {
		super("The Monthly Limit for this user has been exceeded");
	}

	public MonthlyLimitException(String message) {
		super(message);
	}
}
