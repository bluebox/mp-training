package GymPackage;

import java.util.Scanner;

public class GymService {
	static Gym gym = new Gym();
	static Scanner scanner = new Scanner(System.in);
	public static void addMember() {
		int id;
		boolean invalidId=true;
		int age=-1;
		String name;
		boolean isDuplicateid=false;
		do {
			System.out.print("Enter Member ID: ");
			id = Integer.parseInt(scanner.nextLine());
			for(Member mem:gym.getMembers()) {
				if(mem.getMemberId()==id) {
					System.out.println("Duplicate id");
					isDuplicateid=true;
					break;
					//throw new InvalidInput("Duplicate id");
				}
				isDuplicateid=false;
			}
		}while(isDuplicateid);
		System.out.print("Enter Name: ");
		name = scanner.nextLine();

		do {
			System.out.print("Enter Age: ");
			age = Integer.parseInt(scanner.nextLine());
		}
		while(!(age > 0 && age<100)); 

		gym.addMember(new Member(name,age,id));
	}
	public static void assignPlan() {
		System.out.print("Enter Member ID to assign plan: ");
		int memberId = Integer.parseInt(scanner.nextLine());
		Member member = gym.getMemberById(memberId);
		if (member == null) {
			System.out.println("Member not found.");
			return;
		}

		System.out.println("Available Plans:");
		int i = 1;
		for (MembershipPlan plan : gym.getPlans()) {
			System.out.print(i + ". ");
			plan.showPlanDetails();
			i++;
		}
		int planChoice=-1;
		do {
			String out=(planChoice==-1)?"Choose plan number: ":"Invalid plan choice selected";
			System.out.print(out);
			planChoice = Integer.parseInt(scanner.nextLine());
		}while(planChoice < 1 || planChoice > gym.getPlans().size());

		member.assignPlan(gym.getPlans().get(planChoice - 1));
		System.out.println("Plan assigned successfully.");

		if(gym.files) {
			gym.saveMembersToFile();
		}
	}
	public static void getAllMembers() {
		System.out.println("\n--- All Members ---");
		for (Member m : gym.getMembers()) {
			m.showDetails();
		}
	}
	public static void updatePlan() {
		System.out.print("Enter Member ID to assign plan: ");
		int memberUpdateId = Integer.parseInt(scanner.nextLine());
		Member memberUpdate = gym.getMemberById(memberUpdateId);
		if (memberUpdate == null) {
			System.out.println("Member not found.");
			return;
		}
		MembershipPlan updatePlan=memberUpdate.getPlan();
		if(updatePlan.getPlanName()!=null) {
			System.out.println("Already have the plan "+ updatePlan.getPlanName());
			System.out.println("Enter 0 to go back else select a plan number");
		}
		
		System.out.println("Available Plans:");
		int j = 1;
		for (MembershipPlan planUpdate : gym.getPlans()) {
			System.out.print(j + ". ");
			planUpdate.showPlanDetails();
			j++;
		}
		System.out.print("Choose plan number: ");
		int planChoiceUpdate = Integer.parseInt(scanner.nextLine());
		if(planChoiceUpdate==0 && updatePlan!=null) {
			return;
		}
		if (planChoiceUpdate < 1 || planChoiceUpdate > gym.getPlans().size()) {
			System.out.println("Invalid choice.");
		} else {
			if(gym.getPlans().get(planChoiceUpdate - 1)==updatePlan) {
				System.out.println("You choosed the same plan ,No upsdation");
			}else {
				memberUpdate.assignPlan(gym.getPlans().get(planChoiceUpdate - 1));
				System.out.println("Plan assigned successfully.");
			}
		}
		try {
			if(gym.files) {
				gym.saveMembersToFile();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
