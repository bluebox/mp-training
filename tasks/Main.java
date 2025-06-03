import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int id = 10000;
        Scanner scanner = new Scanner(System.in);
        Gym powerHouse = new Gym();
        boolean flag = true;
        int choice,age,duration;
        String name,plan;
        do{
            System.out.println("1. add a member");
            System.out.println("2. Assign/Update a gym membership plan");
            System.out.println("3. View all records of registered members");
            System.out.println("4: Update personal details");
            System.out.println("5. Remove a member from the gym...");
            System.out.println("6. Quit");
            System.out.println("Enter your choice (Enter number only)");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1 ->{
                    System.out.println("Enter name ");
                    name = scanner.nextLine();
                    System.out.println("Enter age ");
                    age = scanner.nextInt();
                    scanner.nextLine();
                    id += 1;
                    powerHouse.addMember(id,name,age);
                }
                case 2 ->{
                    System.out.println("Enter the Id of the member to be assigned/updated a membership plan: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    if (powerHouse.findMember(id)==null){
                        System.out.println("Id not Found");
                    }else{
                        System.out.println("Enter the required plan (don't enter the number) \n1.Basic \n2.Premium \n3.Gold");
                        try {
                            plan = scanner.nextLine().toUpperCase().trim();
                            System.out.println("\n5% discount for 3-5months payment\n10% discount for 6months and above\nEnter the duration in months");
                            duration = scanner.nextInt();
                            powerHouse.assignPlan(id , plan, duration);
                        }
                        catch(IllegalArgumentException e) {
                            System.out.println("Enter Correct Plan name (Choose from Basic, Premium and Gold)");
                        }
                    }
                }
                case 3 -> powerHouse.displayRecords();
                case 4 -> {
                    System.out.println("Enter the id of the person to update his/her personal details...");
                    int remId = scanner.nextInt();
                    Member member = powerHouse.findMember(remId);
                    if(member != null) powerHouse.updatePersonalDetails(member);
                    else System.out.println("Enter a valid id to update the details!");
                }
                case 5 -> {
                    System.out.println("Enter the id of the person to be removed...");
                    int remId = scanner.nextInt();
                    Member member = powerHouse.findMember(remId);
                    if(member != null) powerHouse.removeRecord(member);
                    else System.out.println("Enter a valid id to be removed!");
                }
                case 6 ->{
                    flag = false;
                    System.out.println("Quitting ....");
                }
                default -> System.out.println("invalid choice .... ! try again ...");
            }
        }while(flag);

    }
}
