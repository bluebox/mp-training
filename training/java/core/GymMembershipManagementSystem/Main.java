import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gym g=new Gym();
		Scanner s=new Scanner(System.in);
		int ch;
		while(true)
		{
			System.out.println("======================================================");
			System.out.println("\n====GYM MANAGEMENT SYSTEM====");
			System.out.println("1.Add New Member");
			System.out.println("2.Assign Plan");
			System.out.println("3.View All Members");
			System.out.println("4.Exit");
			System.out.println("Enter Your Choice:");
			try
			{
				ch =s.nextInt();
				s.nextLine();
				if(ch==1)
				{
					System.out.println("Enter Id Of The Member: ");
					String id=s.nextLine();
					System.out.println("Enter Name Of The Member: ");
					String name=s.nextLine();
					System.out.println("Enter Age Of The Memebr: ");
					int age=s.nextInt();
					
					g.addMember(id,name,age);
				}
				else if(ch==2)
				{
					System.out.println("Enter Member Id: ");
					String memberId=s.nextLine();
					System.out.println("Available Plans: ");
					g.showPlans();
					System.out.println("Enter Plan Index: ");
					int planIndex=s.nextInt();
					g.assignPlan(memberId, planIndex);	
				}
				else if(ch==3)
				{
					g.showAllMembers();
				}
				else if(ch==4)
				{
					System.out.println("Exiting...");
					break;
				}
				else
					System.out.println("Invalid Number Entered!");
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());	
			}
		}

	}

}
