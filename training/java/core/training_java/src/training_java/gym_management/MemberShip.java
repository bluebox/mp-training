
import java.time.LocalDate;

public class MemberShip {
	private int durationMonths;
	private double fee;
	private PlanConstants plan;
	private LocalDate joiningDate;

	public MemberShip(PlanConstants plan) {
		this.plan = plan;
		this.joiningDate = LocalDate.now();
		this.durationMonths = plan.getDurationMonths();
		this.fee = plan.getFee();
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
