package GymManagementSystem;

import GymManagementSystem.service.InputValidation;
import GymManagementSystem.service.MemberPlanService;
import GymManagementSystem.service.MemberService;
import GymManagementSystem.service.PlanService;

public class Main {
	public static void main(String[] args) {
		MemberPlanService service = new MemberPlanService();
		MemberService member_service = new MemberService();
		PlanService plan_service = new PlanService();
		InputValidation validation = new InputValidation();
		
		while (true) {
			displayMenu();
			int choice = validation.getIntInput("Enter your choice: ");
			switch (choice) {
			case 1 -> member_service.addMember();
			case 2 -> member_service.updateMember();
			case 3 -> member_service.deleteMember();
			case 4 -> member_service.viewMembers();
			case 5 -> plan_service.addPlan();
			case 6 -> plan_service.updatePlan();
			case 7 -> plan_service.deletePlan();
			case 8 -> plan_service.viewPlans();
			case 9 -> service.mapMemberToPlan();
			case 10 -> service.updateMembership();
			case 11 -> service.deleteMembership();
			case 12 -> service.viewActiveMembers();
			case 13 -> service.getReport();
			case 14 -> service.exportReport();
			case 15 -> {
				System.out.println("Exiting...");
				return;
			}
			default -> System.out.println("Invalid option, try again.");
			}
		}
	}
	private static void displayMenu() {
		System.out.println("\n===== Gym Membership Management System =====");
		System.out.println("1. Add Member");
		System.out.println("2. Update Member");
		System.out.println("3. Delete Member");
		System.out.println("4. View All Members");
		System.out.println("5. Add Plan");
		System.out.println("6. Update Plan");
		System.out.println("7. Delete Plan");
		System.out.println("8. View All Plans");
		System.out.println("9. Assign Plan to Member");
		System.out.println("10. Update Membership");
		System.out.println("11. Delete Membership");
		System.out.println("12. View Active Members");
		System.out.println("13. View Gym Member Details");
		System.out.println("14. Export Gym Member Details");
		System.out.println("15. Exit");
	}
}
