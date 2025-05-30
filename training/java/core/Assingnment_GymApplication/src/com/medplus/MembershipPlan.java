package com.medplus;

public  enum MembershipPlan{BASIC(500),GOLD(1000),PREMIUM(1300);
	
	private int price;

	MembershipPlan(int price) {
		this.price= price;
	}
	


	public int getPrice() {
		return price;
	}



	public void setValue(int price) {
		this.price = price;
	}



	public static void showPlans() {
		System.out.printf("%n%10s -> %s%n%n".formatted("Plan Type","Price"));
		for ( MembershipPlan i : MembershipPlan.values()) {
			System.out.printf("%10s -> %d%n".formatted(i.name(),i.getPrice()));
		}
		
		
	}



} 
