package com.example;

import java.util.Comparator;

public class Compare implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		if(Double.parseDouble(o1.employeeId.substring(3))>Double.parseDouble(o2.employeeId.substring(3))) {
			return 1;
		}
		else if(Double.parseDouble(o1.employeeId.substring(3))==Double.parseDouble(o2.employeeId.substring(3))){
			return 0;
		}
		else {
			return -1;
		}
	}

}
