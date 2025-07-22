package GymManagementSystem;

import GymManagementSystem.controllers.MemberController;
import GymManagementSystem.controllers.MemberPlanController;
import GymManagementSystem.controllers.PlanController;
import GymManagementSystem.service.MemberPlanService;
import GymManagementSystem.service.Impl.MemberPlanServiceImpl;
import GymManagementSystem.utils.InputValidation;

public class Main {
	public static void main(String[] args) {
		MemberPlanController controller = new MemberPlanController();
		MemberController memberController = new MemberController();
		PlanController planController = new PlanController();
		InputValidation validation = new InputValidation();
		MemberPlanService service = new MemberPlanServiceImpl();
		
		while (true) {
			displayMenu();
			int choice = validation.getIntInput("Enter your choice: ");
			switch (choice) {
			case 1 -> memberController.addMember();
			case 2 -> memberController.updateMember();
			case 3 -> memberController.deleteMember();
			case 4 -> memberController.viewMembers();
			case 5 -> planController.addPlan();
			case 6 -> planController.updatePlan();
			case 7 -> planController.deletePlan();
			case 8 -> planController.viewPlans();
			case 9 -> controller.mapMemberToPlan();
			case 10 -> controller.updateMembership();
			case 11 -> controller.deleteMembership();
			case 12 -> controller.viewMembership();
			case 13 -> controller.viewActiveMembers();
			case 14 -> service.getReport();
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
		System.out.println("12. View All Members Membership");
		System.out.println("13. View Active Members");
		System.out.println("14. View Gym Member Details");
		System.out.println("15. Exit");
	}
}
