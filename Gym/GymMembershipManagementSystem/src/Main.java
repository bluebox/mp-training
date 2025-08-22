import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gym g=new Gym();
		Scanner sc=new Scanner(System.in);
		int choice;
		while(true)
		{
			System.out.println("GYM MEMBERSHIP MANAGEMENT SYSTEM");
			System.out.println("1. Add New Gym Member");
			System.out.println("2. Assign Membership Plan to the members");
			System.out.println("3. View All Members");
			System.out.println("Enter your choice: ");
			choice=Integer.parseInt(sc.nextLine());
			try {
				switch(choice)
				{
				case 1:
						System.out.println("Enter Member ID: ");
						String memberId=sc.nextLine();
						System.out.println("Enter Name: ");
						String name=sc.nextLine();
						System.out.println("Enter age: ");
						int age=Integer.parseInt(sc.nextLine());
						g.addMembers(memberId,name,age);
						break;
				case 2:
						System.out.println("Enter MemberID ");
						String MemberId=sc.nextLine();
						System.out.println("Available Plans in the GYM MEMBERSHIP");
						g.showPlans();
						System.out.println("Enter Plan Name to Assign");
						String planName=sc.nextLine();
						g.assignPlanToMember(MemberId,planName);
						break;
				case 3:
						g.viewMembers();
						break;
				case 4:
					    System.out.println("Exiting");
					    break;
				default:
						System.out.println("Invalid Choice");
						break;
				}
			}
		catch(Exception e)
		{
			System.out.println("Invalid Choice");
		}
	}
	}
}