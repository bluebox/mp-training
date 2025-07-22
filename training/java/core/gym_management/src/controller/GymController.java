package controller;

import dao.DataMemberDao;
import dao.DataMembershipPlanDao;
import dao.MemberDao;
import dao.MembershipPlanDao;
import model.Menu;
import service.GymService;

public class GymController {
    private GymService gymService;

    public GymController() {
        MemberDao memberDao = new DataMemberDao();
        MembershipPlanDao planDao = new DataMembershipPlanDao();
        this.gymService = new GymService(memberDao, planDao);
    }

    public void startApplication() {
        System.out.println("Welcome to the Gym Membership Management System!");

        while (true) {
            displayMainMenu();
            int option = gymService.getUserChoice();
            Menu choice = null;
            Menu[] options = Menu.values();
            if(option<0 || option >=7)
            	option=7;
            choice=options[option];
            
            switch (choice) {
            	case IMPORT_MEMBERS: gymService.addNewMembershipPlan();
                	break;
                case ENROLL_MEMBER:
                	gymService.enrollNewMemberProcedure();
                    break;
                case CANCEL_MEMBERSHIP:
                	gymService.cancelMemberProcedure();
                    break;
                case VIEW_ALL_MEMBERS:
                	gymService.showAllRegisteredMembers();
                    break;
                case VIEW_MEMBERSHIP_PLANS:
                	gymService.showAvailableMembershipPlans();
                    break;
                case VIEW_MEMBER_DETAILS:
                	gymService.viewIndividualMemberDetailsProcedure();
                	break;
                case EXIT:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid option. Please enter a number from the menu.");
            }
            System.out.println();
        }
    }

    private void displayMainMenu() {
        System.out.println("--- Main Menu ---");
        System.out.println("0. Add a New Plan");
        System.out.println("1. Enroll a New Member");
        System.out.println("2. Cancel Membership");
        System.out.println("3. View All Registered Members");
        System.out.println("4. View Available Membership Plans");
        System.out.println("5. View Individual Member Details");
        System.out.println("6. Exit Application");
        System.out.print("Please enter your choice: ");
    }

    public static void main(String[] args) {
        GymController application = new GymController();
        application.startApplication();
    }
}
