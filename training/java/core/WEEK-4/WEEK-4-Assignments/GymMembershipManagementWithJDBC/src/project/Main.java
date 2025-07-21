package project;
import java.util.Scanner;

import data.DatabaseConnection;
import data.MemberDBOpearations;
import data.MembershipPlanDBOperations;
import models.Gym;
import models.MembershipPlan;
import utilities.InputUtilities;

public class Main {
	
    public static void main(String[] args) {
        Gym gym = new Gym();
        
        DatabaseConnection.connectToDB("jdbc:mysql://localhost:3306/gym");

        // Predefined plans
        boolean flag=MembershipPlanDBOperations.selectAllAndStoreLocally(gym);
        if(!flag) {
        	MembershipPlan.storePredefinedPlansLocally(gym);
        	MembershipPlanDBOperations.addAllPlans(gym.getPlans());
        }
        
        MemberDBOpearations.selectAllAndStoreLocally(gym);

        Scanner scanner = new Scanner(System.in);
        InputUtilities.inputMain(scanner,gym);
        
        DatabaseConnection.closeStatement();
        DatabaseConnection.closeStatement();
        
    }
}

