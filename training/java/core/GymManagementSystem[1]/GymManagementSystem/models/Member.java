package GymManagementSystem.models;

public class Member extends Person {
	private int memberId;

	public Member(String name, int age, int memberId) {
		super(name, age);
		this.memberId = memberId;
	}
	
	public Member(String name, int age) {
	    super(name, age);
	}
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Override
	public void showDetails() {
		System.out.println("--------------------------------------------------");
		System.out.println("Member ID   : " + memberId);
		System.out.println("Name        : " + getName());
		System.out.println("Age         : " + getAge());
		System.out.println("--------------------------------------------------");
	}
	
	@Override
	public String toString() {
	    return "Member ID: " + memberId +
	           ", Name: " + getName() +
	           ", Age: " + getAge();
	}
}
