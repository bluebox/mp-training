
public enum PlanConstants {
BASIC(1548.43,3),
PREMIUM(4087.76,6),
GOLD(8765.43,12);
	private final double fee;
	private final int durationMonths;
	private PlanConstants(double fee,int durationMonths) {
		this.fee=fee;
		this.durationMonths=durationMonths;
	}
	public double  getFee() {
		return fee;
	}
	public int  getDurationMonths() {
		return durationMonths;
	}
}
