
import java.time.LocalDate;

enum Plan {
	BASIC, PREMIUM, GOLD
}

public class MemberShip {
	private int durationMonths;
	private double fee;
	private Plan plan;
	private LocalDate joiningDate;

	public MemberShip(Plan plan) {
		this.plan = plan;
		this.joiningDate = LocalDate.now();
		{
			switch (plan.ordinal())

			{
			case 0:
				this.durationMonths = 3;
				this.fee = 1548.43;
				break;
			case 1:
				this.durationMonths = 6;
				this.fee = 4087.76;
				break;
			case 2:
				this.durationMonths = 12;
				this.fee = 8765.43;
				break;
			default:
				this.durationMonths = 0;
				this.fee = 0;
			}
		}

	}

	public int getDuration() {
		return durationMonths;

	}

	public String getFee() {
		return " %s ".formatted(fee);
	}

	public String getJoiningDate() {
		return joiningDate.toString();
	}

	public String getPlan() {
		return "%s".formatted(plan);
	}

	public void getMemberShipDetails() {
		System.out.print("The Plan Details are : ");
		System.out.println();
		System.out.println("Plan Type : " + this.getPlan());
		System.out.println("DurationMonths : " + this.getDuration());
		System.out.println("Fee : " + this.getFee());
		System.out.println("JoiningDate : "+this.getJoiningDate());
	}

}
