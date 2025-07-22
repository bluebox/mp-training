package project;
import java.util.Scanner;

import controller.GymController;
import daoImplementation.MemberDAOImplementation;
import daoImplementation.MembershipPlanDAOImplementation;
import db.DatabaseConnection;
import serviceImplementation.GymServiceImplementation;
import serviceImplementation.MembershipPlanServiceImplementation;

public class Main {
	
    public static void main(String[] args) {
        GymServiceImplementation gym = new GymServiceImplementation();
        
//        DatabaseConnection
        DatabaseConnection.connectToDB("jdbc:mysql://localhost:3306/gym");

        // Predefined plans
        boolean flag=new MembershipPlanDAOImplementation().selectAllAndStoreLocally(gym);
        if(!flag) {
        	new MembershipPlanServiceImplementation().storePredefinedPlansLocally(gym);
        	new MembershipPlanDAOImplementation().addAllPlans(gym.getPlans());
        }
        
        
        new MemberDAOImplementation().selectAllAndStoreLocally(gym);

        Scanner scanner = new Scanner(System.in);
        GymController.inputMain(scanner,gym);
        
        DatabaseConnection.closeStatement();
        DatabaseConnection.closeStatement();
        
    }
}

