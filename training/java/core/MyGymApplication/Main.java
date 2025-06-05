package MyGymApplication;

import java.util.regex.*;
import java.util.Scanner;


public class Main {

    public static boolean isOnlyAlphabets(String input) {
        return input.matches("[a-zA-Z]+");
    }
    public static boolean isOnlyNumbers(String input) {
        return input.matches("\\d+");
    }

    public static String getValidName(Scanner sc){

        String name;
        System.out.println("Enter your name :");
        
        name = sc.nextLine();

        while(!isOnlyAlphabets(name)) {
            System.out.println("Enter characters only");
            System.out.println("Enter your name :");
            name = sc.nextLine();
        }
        return name;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MyGym beFit = new MyGym();
        System.out.println("-".repeat(20));

        Trainer trainer1 = new Trainer("venkat" , 35);
        Trainer trainer2 = new Trainer("bharath" , 40);
        Trainer trainer3 = new Trainer("raju" , 33);

        beFit.addTrainer(trainer1);
        beFit.addTrainer(trainer2);
        beFit.addTrainer(trainer3);

        Member member1 = new Member("reddysekhar" , 23 , "basic" , trainer1);
        Member member2 = new Member("saketh" , 23 , "gold" );
        Member member3 = new Member("venu" , 24 , "premium" , trainer2);

        
        beFit.addMember(member1);
        beFit.addMember(member2);
        beFit.addMember(member3);

        //System.out.println("------------------------------");

        boolean looping = true;
        while (looping){

            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Welcome to Befit Gym");
            
            System.out.println("1.Admin    2.Existing Member    3.New Member Registration   \n4.Trainer    5.Membership Plans   q.Quit");

            String ch = sc.nextLine();

            switch(ch){
                
                case "1" -> {
                    if(beFit.takeAdminDetails(sc)){
                        boolean inLoop = true;

                        while(inLoop){
                            System.out.println("choose your operation : (q -> quit)");
                            System.out.println("""

                                    1.Add a member
                                    2.Add a trainer
                                    3.Remove a member by name
                                    4.Remove a member by id
                                    5.Remove a trainer by name
                                    6.Remove a trainer by id
                                    7.Get member details by name
                                    8.Get member details by id
                                    9.Get trainer details by name
                                    10.Get trainer details by id
                                    11.Get all member details
                                    12.Get all trainer details
                                    13.Get trainee list of trainer
                                    """);

                                

                                //String choice = sc.nextLine();

                                int op = 0;
                                boolean isValid = false;

                                while (!isValid) {
                                    System.out.println("choose your operation : (q -> quit)");
                                    
                                    try {
                                        op = Integer.parseInt(sc.nextLine());
                                        
                                        if (op >= 1 && op <= 13) {
                                            isValid = true;
                                        } else {
                                            System.out.println("must be between 1 and 13. Try again.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a number.");
                                    }
                                }

                                switch (String.valueOf(op)) {
                                    case "1" ->   beFit.createMember( sc);
                                    
                                    case "2" -> beFit.createTrainer( sc);
                                    
                                    case "3" -> {
                                        
                                        beFit.removeMember(beFit.getMemberByValidName(sc));
                                        System.out.println("removed successfully..\n----------------------------");
                                    }

                                    case "4" -> {
                                        
                                        beFit.removeMember(beFit.getMemberByValidId(sc));
                                        System.out.println("removed successfully");

                                    }
                                    case "5" -> {
                                        
                                        beFit.removeTrainer(beFit.getTrainerByValidName(sc));
                                        System.out.println("removed successfully..\n----------------------------");
                                        
                                    }

                                    case "6" -> {
                                        
                                        beFit.removeTrainer(beFit.getTrainerByValidId(sc));
                                        System.out.println("removed successfully..");

                                    }
                                    case "7" -> System.out.println(beFit.getMemberByValidName(sc).showMemberDetails());
                                    
                                    case "8" -> System.out.println(beFit.getMemberByValidId(sc).showMemberDetails());
                                    
                                    case "9" -> beFit.getTrainerByValidName(sc).details();
                                    
                                    case "10" -> beFit.getTrainerByValidId(sc).details();
                                    
                                    case "11" ->  beFit.gymMemberDetails();
                                    
                                    case "12" -> {
                                        beFit.gymTrainerDetails();
                                        System.out.println("-------------------------------");
                                    }
                                    case "13" -> beFit.getTrainerByValidName(sc).showTrainees();
                                  
                                    case "q" -> {
                                        inLoop = false;
                                        break;
                                    }
                                    case "Q" -> {
                                        inLoop = false;
                                        break;
                                    }
                                }
                        }
                    }
                }

                case "2" -> {
                    //System.out.println("choose your operation : (q -> quit)");
                    System.out.println("""
                            1.Get your details by name
                            2.Get your details by id
                            """);
                    //String choice = sc.nextLine();

                    int op = 0;
                    boolean isValid = false;

                    while (!isValid) {
                        System.out.println("choose your operation : (q -> quit)");
                        
                        try {
                            op = Integer.parseInt(sc.nextLine());
                            
                            if (op >= 1 && op <= 2) {
                                isValid = true;
                            } else {
                                System.out.println("must be between 1 and 2. Try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                    }

                    switch(String.valueOf(op)){
                        case "1" -> System.out.println( beFit.getMemberByValidName(sc).showMemberDetails());                 

                        case "2" -> System.out.println( beFit.getMemberByValidId(sc).showMemberDetails());        
                    
                    }
                    
                }
                
                case "3" -> beFit.createMember( sc);
                

                case "4" -> {
                    System.out.println("""
                            1.get your details by name
                            2.get your details by id
                            3.show your trainee list
                            """);
                    //String op = sc.nextLine();

                    int op = 0;
                    boolean isValid = false;

                    while (!isValid) {
                        System.out.println("choose your operation : (q -> quit)");
                        
                        try {
                            op = Integer.parseInt(sc.nextLine());
                            
                            if (op >= 1 && op <= 3) {
                                isValid = true;
                            } else {
                                System.out.println("must be between 1 and 3. Try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                    }

                    switch(String.valueOf(op)){

                        case "1" -> beFit.getTrainerByValidName(sc).details();
                       
                        case "2" -> beFit.getTrainerByValidId(sc).details();
                        
                        case "3" -> beFit.getTrainerByValidName(sc).showTrainees();

                    }
                }

                case "5" -> MembershipPlan.showPlans();
                
                case "q" ->{
                    looping = false;
                    break;
                }

                case "Q" -> {
                    looping = false;
                    break;
                }

                default ->   System.out.println("Enter valid option (1 or 2 or 3 or 4)");
                

            }

        }
        
    }
}
