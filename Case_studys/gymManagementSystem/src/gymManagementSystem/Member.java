package gymManagementSystem;
import exceptions.NoPlanException;

public class Member extends Person{
	
	private static int currCntOfMembers = 0; // auto increment
	{
		currCntOfMembers+=1;
	}
	
	private int memberId;
	private MemberShipPlan myPlan;
	
	Member(String name,int age){
		super(name,age);
		memberId = currCntOfMembers;
		myPlan = null;
	}
	
	public void setMemberShip(MemberShipPlan myPlan) { // setter function membership
		try {
			this.myPlan = myPlan;
			if(myPlan == null) {
				throw new NoPlanException();
			}
		}catch(NoPlanException npe){
			npe.msg();
		}
	}
	public MemberShipPlan getMemberShip() { // getter function for membership
		return this.myPlan;
	}
	
	public int getMemberId() { //getter for id attr
		return this.memberId;
	}
	
	@Override
	public String toString() {
		return super.toString()+" currentPlan= " + myPlan.toString();
	}
	
}
