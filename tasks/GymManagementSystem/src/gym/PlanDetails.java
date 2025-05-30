package gym;

public enum PlanDetails {
		BASIC (800),
		GOLD (1200),
		PREMINUM (2000);
		int price;
		PlanDetails(int price)
		{
			this.price=price;
			
		}

}
