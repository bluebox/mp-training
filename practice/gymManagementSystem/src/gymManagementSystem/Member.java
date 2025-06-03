package gymManagementSystem;
import exceptions.NoPlanException;

public class Member extends Person{
	
	private static int currCntOfMembers = 0;
	{
		currCntOfMembers+=1;
	}
	
	private int memberId;
	private MembershipPlan myPlan;
	
	Member(String name,int age){
		super(name,age);
		memberId = currCntOfMembers;
		myPlan = null;
	}
	
	public void setMemberShip(MembershipPlan myPlan) {
		try {
			this.myPlan = myPlan;
			if(myPlan == null) {
				throw new NoPlanException();
			}
		}catch(NoPlanException npe){
			npe.msg();
		}
	}
	
	public int getMemberId() {
		return this.memberId;
	}
	
}
