import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    private static Gym gym = new Gym("UCAN");
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        gym.addMember(1234567890,"Aravind", 43,"MALE");
        gym.addMember(2345678901L,"Bharath", 32,"MALE");
        gym.addMember(5678901234L,"Chandu", 26,"MALE");
        gym.addTrainer(3456789012L,"BASIC","Dhanush",28,"MALE");
        gym.addTrainer(4567890123L,"GOLD","Ravi",35,"MALE");
        gym.addTrainer(6789012345L,"PREMIUM","Sarath",38,"MALE");
        
        while (!exit) {
            printMenu();
            int choice = getValidChoice();
    
            switch (choice) {
                case 1:
                    addNewMember(gym, sc);
                    break;
                case 2:
                    assignMembershipPlan();
                    break;
                case 3:
                    addNewTrainer(gym,sc);
                    break;
                case 4:
                    additionalInfo();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    exit = true;
                    break;
                default:
                    System.out.println("Please select a valid option (1–5).\n");
            }
        }
        sc.close();
    }
    
    private static void printMenu() {
        System.out.println("---- Gym Membership System ----");
        System.out.println("1. Add New Member");
        System.out.println("2. Assign Membership Plan");
        System.out.println("3. Add New Trainer");
        System.out.println("4. Additional Information");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }
    
    private static void additionalInfo() {
        try {
            System.out.println("\n-- Additional Information --");
            System.out.println("1. Delete Member");
            System.out.println("2. Delete Trainer");
            System.out.println("3. View All Members");
            System.out.println("4. View All Trainers");
            System.out.println("5. Assign the Trainer");
            System.out.println("6. Update Member Name");
            System.out.println("7. Update Member Age");
            System.out.println("8. Get Member By Id");
            System.out.print("Choose an option: ");
    
            int choice = Integer.parseInt(sc.nextLine());
    
            switch (choice) {
                case 1:
                    deleteMember();
                    break;
    
                case 2:
                    deleteTrainer();
                    break;
    
                case 3:
                    gym.getAllMembersDetails();
                    break;
    
                case 4:
                    gym.getAllTrainersDetails();
                    break;
                case 5:
                    assignTrainer();
                    break;
                case 6:
                    updateMemberName();
                    break;
                case 7:
                    updateMemberAge();
                    break;
                case 8:
                    Member m=getMember();
                    if(m==null)
                    {
                      System.out.println("Member Not Found");   
                    }
                    else{
                        m.showDetails();
                    }
                    break;
                default:
                    System.out.println("Invalid choice in additional info.\n");
            }
    
        } catch (Exception e) {
            System.out.println("Error in additional information: " + e.getMessage() + "\n");
        }
    }

    private static int getValidChoice() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.\n");
            return -1;
        }
    }

    private static void addNewMember(Gym gym, Scanner sc) {
        long memberId =getMemberId(sc);
        String name =getName(sc);
        int age =getAge(sc);
        String gender =getGender(sc);
        
        gym.addMember(memberId, name, age, gender.equals("F") ? "FEMALE" : "MALE");
        System.out.println("Member added successfully.\n");
    }
    
    private static void addNewTrainer(Gym gym, Scanner sc) {
        long memberId =getMemberId(sc);
        String planName=getPlanName(sc);
        String name =getName(sc);
        int age =getAge(sc);
        String gender =getGender(sc);
        
        gym.addTrainer(memberId,planName,name, age, gender.equals("F") ? "FEMALE" : "MALE");
        System.out.println("Member added successfully.\n");
    }

    private static void assignMembershipPlan() {
        long memberId;
        Member member = null;
    
        // Validate Member ID and get Member
        while (true) {
            System.out.print("Enter Member ID (10 digits): ");
            String idInput = sc.nextLine().trim();
    
            if (!idInput.matches("\\d{10}")) {
                System.out.println("Error: Member ID must be exactly 10 digits.");
                continue;
            }
    
            try {
                memberId = Long.parseLong(idInput);
                member = gym.getMember(memberId);
                if (member == null) {
                    System.out.println("Error: Member not found.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Member ID must be a valid number.");
            }
        }
    
        // Show plans and validate input
        String planName;
        while (true) {
            gym.showAvailablePlans();
            System.out.print("Enter Plan Name to assign (BASIC / PREMIUM / GOLD): ");
            planName = sc.nextLine().trim().toUpperCase();
    
            if (planName.equals("BASIC") || planName.equals("PREMIUM") || planName.equals("GOLD")) {
                break;
            } else {
                System.out.println("Error: Invalid plan name. Please enter BASIC, PREMIUM, or GOLD.");
            }
        }
    
        gym.assignPlan(member, planName);
        System.out.println("Plan assigned successfully.\n");
    }
    
    private static long getMemberId(Scanner sc)
    {
          // Member ID
        long memberId=0;
        while (true) {
            System.out.print("Enter Member ID (10 digits): ");
            String idInput = sc.nextLine().trim();
            if (!idInput.matches("\\d{10}")) {
                System.out.println("Error: Member ID must be exactly 10 digits.");
                continue;
            }
            try {
                memberId = Long.parseLong(idInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Member ID must be a valid number.");
            }
        }
        return memberId;
    }
    private static long getTrainerId(Scanner sc)
    {
          // Trainer ID
        long trainerId=0;
        while (true) {
            System.out.print("Enter Trainer ID (10 digits): ");
            String idInput = sc.nextLine().trim();
            if (!idInput.matches("\\d{10}")) {
                System.out.println("Error: Trainer ID must be exactly 10 digits.");
                continue;
            }
            try {
                trainerId = Long.parseLong(idInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Trainer ID must be a valid number.");
            }
        }
        return trainerId;
    }
    
    private static String getName(Scanner sc)
    {
          // Name
        String name="";
        while (true) {
            System.out.print("Enter Name: ");
            name = sc.nextLine().trim();
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Error: Name must contain only letters and spaces.");
            } else {
                break;
            }
        }
        return name;
    }
    
    private static int getAge(Scanner sc)
    {
           // Age
        int age=0;
        while (true) {
            System.out.print("Enter Age: ");
            try {
                age = Integer.parseInt(sc.nextLine());
                if (age <= 0) {
                    System.out.println("Error: Age must be a positive number.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Age must be a valid number.");
            }
        }
        return age;
    }
    
    private static String getGender(Scanner sc)
    {
        String gender="";
        // Gender
        while (true) {
            System.out.print("Enter Gender (M/F/O): ");
            gender = sc.nextLine().trim().toUpperCase();
            if (!gender.matches("[MFO]")) {
                System.out.println("Error: Gender must be M, F, or O.");
            } else {
                break;
            }
        }
        return gender;
    }
    
    private static String getPlanName(Scanner sc)
    {
        String planName="";
        while (true) {
            System.out.print("Enter Plan Name to assign (BASIC / PREMIUM / GOLD): ");
            planName = sc.nextLine().trim().toUpperCase();
    
            if (planName.equals("BASIC") || planName.equals("PREMIUM") || planName.equals("GOLD")) {
                break;
            } else {
                System.out.println("Error: Invalid plan name. Please enter BASIC, PREMIUM, or GOLD.");
            }
        }
        return planName;
    }
    
    private static void deleteMember()
    {
        System.out.print("Enter Member ID to delete: ");
        long memberId = getMemberId(sc);
        Member member = gym.getMember(memberId);
        if (member != null) 
        {
            gym.deleteMember(member);
            System.out.println("Member deleted successfully.\n");
        } else {
            System.out.println("Member not found.\n");
        }
    }
    
    private static void deleteTrainer()
    {
        System.out.print("Enter Trainer ID to delete: ");
        long trainerId = Long.parseLong(sc.nextLine());
        Trainer trainer = gym.getTrainer(trainerId);
        if (trainer != null) {
            gym.deleteTrainer(trainer);
            System.out.println("Trainer deleted successfully.\n");
        } 
        else 
        {
            System.out.println("Trainer not found.\n");
        }
    }
    private static void assignTrainer()
    {
        System.out.print("Enter Member ID: ");
        long memId = getMemberId(sc);
        Member mem = gym.getMember(memId);
        System.out.print("Enter Trainer ID:");
        String planName=getPlanName(sc);
        gym.showTrainersOfPlan(planName);
        long trainId=getTrainerId(sc);
        Trainer train=gym.getTrainer(trainId);
        if (mem != null && train!=null) {
            boolean prin=gym.assignTrainer(mem,train);
            if(prin) {
            System.out.println("Trainer Assigned successfully.\n");
            }
        } else if(mem==null){
            System.out.println("Member not found.\n");
        }
        else if(train==null){
            System.out.println("Trainer not found.\n");
        }
        else{
            System.out.println("Trainer  and Member not found.\n");
        }
    }
    private static void updateMemberAge()
    {
        long memberId=getMemberId(sc);
        int age=getAge(sc);
        gym.updateMemberAge(memberId,age);
    }
    private static void updateMemberName()
    {
        long memberId=getMemberId(sc);
        String name=getName(sc);
        gym.updateMemberName(memberId,name);
    }
    private static Member getMember()
    {
        long id=getMemberId(sc);
        return gym.getMember(id);
    }
}
