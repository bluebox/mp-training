package com.Case_Study_1;

import java.util.Scanner;

public class Gym_Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gym gym = new Gym();
        boolean loop = true;

        while (loop) {
            System.out.println("1. Add Member");
            System.out.println("2. Assign Plan");
            System.out.println("3. Show Members");
            System.out.println("4. Show Plans");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    gym.addMember(id, name, age);
                    break;

                case 2:
                    System.out.print("Member ID: ");
                    String memId = sc.nextLine();
                    gym.showPlans();
                    System.out.print("Choose plan: ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    gym.assignPlan(memId, p - 1);
                    break;

                case 3:
                    gym.showAllMembers();
                    break;

                case 4:
                    gym.showPlans();
                    break;

                case 5:
                    loop = false;
                    System.out.println("Bye!");
                    break;

                default:
                    System.out.println("Invalid!");
            }
        }

        sc.close();
    }
}
