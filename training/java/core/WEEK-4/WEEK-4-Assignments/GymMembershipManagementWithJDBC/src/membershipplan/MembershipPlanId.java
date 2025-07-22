package membershipplan;

public enum MembershipPlanId {
	BASIC,PREMIUM,GOLD;
	
	public int getOneBasedOrdinal() {
		return this.ordinal()+1;
	}
}
