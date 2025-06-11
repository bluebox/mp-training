package GymCaseStudy;

import GymCaseStudy.Gym;

import java.util.Scanner;
import  java.util.ArrayList;

public class MainClass {

    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        Gym myGym=new Gym();
        boolean f=true;
        while(f){
            System.out.println("""
				WELCOMETO OUR GYM!!!
				please select your option.
				1)login as a gym manager.
				2)register as a new user.
				3)register as a new trainer.
				4)enter '4' to exit.
				""");
            int userOption = scanner.nextInt();
            scanner.nextLine();
            switch(userOption) {
                case 1:{
                    int count=0;
                    boolean login=false;
                    do{
                        System.out.println("enter loginId:");
                        String logInId=scanner.nextLine();
                        System.out.println("enter Passworrd:");
                        String logInPassword=scanner.nextLine();
                        login=myGym.checkLogin(logInId, logInPassword);
                        count+=1;
                        if(login) {
                            System.out.println("hai "+myGym.getName()+" Welcome");
                        }
                        else {System.out.print("wrong id or  password.please try again");
                            scanner.nextLine();
                        }
                    }
                    while(count<3 && !login);
                    if(!login) {
                        System.out.println("you crossed the number of limits.Thank you !!!");
                    }
                    else {
                        boolean T=true;

                        while(T) {
                            System.out.println("""
						dear Durga Prasad you have the following options.
						please select one out of these.
						1)to get members list and their details.
						2)to get trainers list and their details.
						3)To add a new user.
						4)To add a new trainer.
						5)Enter '5' to Exit.
						""");
                            int a=scanner.nextInt();
                            scanner.nextLine();
                            switch(a) {
                                case 1:myGym.membersDetails();break;
                                case 2:myGym.trainersDetails();break;
                                case 3:{
                                    System.out.println("enter the name of the member:");
                                    String name=scanner.nextLine();
                                    System.out.println("enter the age of the member:");
                                    int age=scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("enter the address of the member:");
                                    String address = scanner.nextLine();
                                    System.out.println("enter the weight of the member:");
                                    double weight=scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("enter the PlanType of the member:");
                                    String planType=scanner.nextLine();
                                    System.out.println("enter the start Date of the member:");
                                    String startDate=scanner.nextLine();
                                    Members newUser=new Members(name,age,address,weight,planType,startDate);
                                    myGym.addUser(newUser);
                                    break;}
                                case 4:{
                                    System.out.println("enter the name of the trainer:");
                                    String name=scanner.nextLine();
                                    System.out.println("enter the age of the trainer:");
                                    int age =scanner.nextInt();
                                    System.out.println("enter the Experiance  of the trainer:");
                                    int experiance =scanner.nextInt();
                                    Trainers trainer=new Trainers(name,age,experiance);
                                    myGym.addTrainer(trainer);
                                    break;}
                                case 5:{T=false;break;}

                            }

                        }
                    }

                    break;}
                case 2:{
                    System.out.println("enter your name :");
                    String name=scanner.nextLine();
                    System.out.println("enter your age:");
                    int age=scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("enter your address:");
                    String address = scanner.nextLine();
                    System.out.println("enter your weight:");
                    double weight=scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("""
					Select your plan:
					1)Basic-3 months -$300/-
					2)premium-6 months -$500/-
					3)Gold-9 months -$900/-
					""");
                    String planType=scanner.nextLine();
                    System.out.println("enter your preferable Date of Starting:");
                    String startDate=scanner.nextLine();
                    System.out.println("""
                Did you need a trainers?
                Yes (Y)/No (N)""");
                    String b=scanner.nextLine();
                    if (b.equalsIgnoreCase("Y")){
                        System.out.println("select your trainer name");
                        myGym.displayTrainers();
                        String trainer=scanner.nextLine();


                        Members newUser=new Members(name,age,address,weight,planType,startDate,trainer);
                        myGym.addCoustomer(newUser,trainer);
                        myGym.addUser(newUser);
                    }
                    else{
                        Members newUser=new Members(name,age,address,weight,planType,startDate);
                        myGym.addUser(newUser);}

                    System.out.println("Thank you.your registartion was succesfully completed.");
                    break;}
                case 3:{
                    System.out.println("enter your name ::");
                    String name=scanner.nextLine();
                    System.out.println("enter your age:");
                    int age =scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("enter your experiance in years:");
                    int experiance =scanner.nextInt();
                    scanner.nextLine();
                    Trainers trainer=new Trainers(name,age,experiance);
                    myGym.addTrainer(trainer);

                    System.out.println("Thank you.your registartion was succesfully completed.");
                    break;}
                case 4:{f=false;
                    break;}
            }
        }}

}
