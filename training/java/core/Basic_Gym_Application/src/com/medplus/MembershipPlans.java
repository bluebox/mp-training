package com.medplus;

public enum MembershipPlans {
	BASIC(500), GOLD(1000), PREMIUM(1300);

	private int price;

	MembershipPlans(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setValue(int price) {
		this.price = price;
	}

	public static void showPlans() {
		System.out.printf("%10s -> %s".formatted("Plan Type", "Price%n"));
		int count = 1;
		for (MembershipPlans i : MembershipPlans.values()) {
			System.out.printf("%d. %10s -> %d%n".formatted(count, i.name(), i.getPrice()));
			count++;
		}
		System.out.println("Select a Plan : ");
	}
}
