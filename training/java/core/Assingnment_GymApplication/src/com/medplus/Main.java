package com.medplus;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        int typeOfMember;
        Gym gym = new Gym();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Best Gym Welcomes you !!!");

        while (true) {

            System.out.print("\n\n1. Member   2. Trainer\n3. New Membership   4. View Plans \n 5.Quit\n\n");
            typeOfMember = Input.getNumberInRange(1, 5);

            if (typeOfMember == 3) {

                gym.addMember(Input.getValidatedName(), Input.calculateAge(Input.getValidatedDOB()), LocalDate.now(), Input.getValidatedMembershipPlan());


            } else if (typeOfMember == 1) {
                System.out.print("\nEnter your Membership Id: ");
                MemberController.getMyDetails(Input.getNumberInRange(10000,100000));

            } else if (typeOfMember == 5) {
                System.out.println("\n\nHave a Nice Day !!!");
                break;
            }else if(typeOfMember == 2) {
            	new TrainerControls() {};
            }
            else if (typeOfMember ==4) {
            	System.out.println("\nThe Plans Available ");
            	MembershipPlans.showPlans();
            }
        }

        scanner.close();
    }
}
