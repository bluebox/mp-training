package controller;

import java.util.Scanner;

import serviceImplementation.GymServiceImplementation;
import utilities.InvalidInputException;

public class PersonController {
	public static int inputAge(Scanner scanner) {
		int age;
        while (true) {
            System.out.print("Enter Age: ");
            String input = scanner.nextLine();
            try {
                age = Integer.parseInt(input);
                if (age > 0 && age<=100) break;
                else throw new InvalidInputException("Age must be positive and must below 100, Enter valid Age...");
            } catch (NumberFormatException|InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        return age;
	}
	
	public static String inputPhone(GymServiceImplementation gym,Scanner scanner) {
		String phone;
        while (true) {
            System.out.print("Enter Phone Number: ");
            try {
            	phone = scanner.nextLine();
                if(phone.length()!=10) {
                	throw new InvalidInputException("Phone number must be of 10 digits only...");
                }
                if(!phone.matches("^[0-9]{10}$")) {
                	throw new InvalidInputException("Phone number should contain only digits...");
                }
                break;
            }catch(InvalidInputException e) {
            	System.out.println(e.getMessage());
            }
        }
        return phone;
	}
}
