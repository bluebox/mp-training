package com.junit;

public class Methods {
	
	public int add(int a , int b) {
		return a+b;
	}
	
	private int sub(int a , int b) {
		return a-b;
	}
	
	protected int mul(int a, int b) {
		return a*b;
	}
	
	public int div(int a,int b) {
		if (b==0) {
			throw new  IllegalArgumentException("Cannot divide by Zero .");
		}
		return a/b;
	}

}
