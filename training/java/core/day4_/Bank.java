package com.day4_;

import java.util.ArrayList;

public class Bank {
	public String name;
	private ArrayList<Customer> cusms=new ArrayList<>(1000);
	public Bank(String name) {
		this.name = name;
	}
	public ArrayList<Customer> getCusms() {
		return cusms;
	}
	public int addCusms(Customer cusmr) {
		for(Customer cs:this.cusms) {
			if(cs.name.equals(cusmr.name)) {
				return -1;
			}
		}
		this.cusms.add(cusmr);
		return 1;
	}
	
	
}
