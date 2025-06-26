package MyGymApplication;
import java.util.ArrayList;
import java.util.Scanner;
public class MyGym implements Gym{

    private String gymName = "beFit gym";
    private String gymLocation = "Hyderabad";
    private String managerName = "naga";
    private String managerId = "befit10001";
    private String managerPassword = "naga";
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

    MyGym(){
        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    MyGym(String managerId , String managerPassword){
        this.managerId = managerId;
        this.managerPassword = managerPassword;
        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    public String getManagerName(){
        return this.managerName;
    }
    public String getManagerPassword(){
        return this.managerPassword;
    }

    public void addMember(Member obj){
        members.add(obj);
    }

    public void removeMember(Member obj){
        if( members.remove(obj) ) Member.membersCount--;
    }

    public void addTrainer(Trainer obj){
        trainers.add(obj);
    }

    public void removeTrainer(Trainer obj){
        trainers.remove(obj);
    }

    public void gymDetails(){
        System.out.println("-".repeat(20));
        System.out.println("GYM DETAILS");
        System.out.println("Gym name : "+ this.gymName + " location : " + this.gymLocation);
    }

    public String toString(){
        return "gym name : %s ,location : %s , gym manager : %s".formatted(this.gymName , this.gymLocation,this.managerName);
    }

    public void gymManagerDetails(){
        System.out.println("-".repeat(20));
        System.out.println("GYM MANAGER DETAILS");
        System.out.println("Manager name : "+this.managerName + " manager id : "+ this.managerId);
    }

    public void gymMemberDetails(){
        System.out.println("-".repeat(20));
        System.out.println("GYM MEMBERS DETAILS");
        if(members.size() > 0){
            for(Member mem : members){
            System.out.println(mem.showMemberDetails());
            }
        }
        else{
            System.out.println("Gym has no members");
        }
        
    }

    public void gymMemberNames(){
        System.out.println("-".repeat(20));
        System.out.println("GYM MEMBERS NAMES");
        if(members.size() > 0){
            for(Member mem : members){
            System.out.println(mem.getName());
            }
        }
        else{
            System.out.println("Gym has no members");
        }
        
    }


    public Trainer getTrainerByName(String name){
        
        for(Trainer obj: trainers){
            if(obj.getName().equalsIgnoreCase(name)){
                return obj;
            }
        }
        return null;
    }

    public Trainer getTrainerById(String id){
        
        for(Trainer obj: trainers){
            if(obj.getTrainerId().equals(id)){
                return obj;
            }
        }
        return null;
    }

    public  Member getMemberByName(String name){
        
        for(Member obj: members){
            if(obj.getName().equalsIgnoreCase(name)){
                return obj;
                
            }
        }
        return null;
    }

    public  Member getMemberById(String id){
        
        for(Member obj: members){
            if(obj.getMemberId().equals(id)){
                return obj;              
            }
        }
        return null;
    }
    
    public void gymTrainerDetails(){
        System.out.println("-".repeat(20));
        System.out.println("GYM TRAINERS :");
        if(trainers.size() > 0){
            for(Trainer trainer : trainers){
            trainer.details();
            }
        }
        else{
            System.out.println("This Gym has no trainers");
        }
        
    }

    public void gymTrainerNames(){
        System.out.println("-".repeat(20));
        System.out.println("GYM TRAINERS :");
        int i = 1;
        if(trainers.size() > 0) {
            for(Trainer trainer : trainers){
                System.out.println(i +": " +trainer.getName());
                i++;
            }
        }
        else{
            System.out.println("Gym has no trainers");
        }
        
    }

    public Trainer getTrainerByIndex(int idx){
       return trainers.get(idx - 1);    
    }
    
    public void gymMembershipPlans(){
        MembershipPlan.showPlans();
    }

    public void createMember(Scanner sc){
        
        System.out.println("Enter your details");
        System.out.println("Name :");
        String name ;
        name= sc.nextLine();
        while(!Main.isOnlyAlphabets(name)){
            System.out.println("Enter valid data :");
            name = sc.nextLine();
        }

        // System.out.println("Enter age :");

        // String checkAge ;
        // checkAge = sc.nextLine();

        // while(!Main.isOnlyNumbers(checkAge)){
        //     System.out.println("Enter digits only :");
        //     checkAge = sc.nextLine();
        // }
        int age = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Enter your age (10-80): ");
            
            try {
                age = Integer.parseInt(sc.nextLine());
                
                if (age >= 10 && age <= 80) {
                    isValid = true;
                } else {
                    System.out.println("Age must be between 10 and 80. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        

        
        // int age = Integer.parseInt(checkAge);
        
        this.gymMembershipPlans();
        
        //System.out.println("Choose Your Plan");


        int ch = 0 ;
        // sc.nextLine();
        String plan = "";

        isValid = false;

        while (!isValid) {
            System.out.print("choose your plan");
            
            try {
                ch = Integer.parseInt(sc.nextLine());
                
                if (ch > 0 && ch < 4) {
                    isValid = true;
                } else {
                    System.out.println("must be between 1 and 3. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        
            switch(ch){
            case 1 -> {System.out.println("You have selected base plan"); plan="basic";}
            
            case 2 -> {System.out.println("You have selected gold plan"); plan="gold";}

            case 3 -> {System.out.println("You have selected premium plan"); plan="premium";}
        }
        
        

        System.out.println("Do you need trainer (additional cast -> 500/month) (Y/N) :");
        String op;
        op = sc.nextLine().toLowerCase();

        Trainer trainer = null;
        
        if("yes".contains(op)){
            //System.out.println("choose your trainer (enter no.) :");
            this.gymTrainerNames();
            // op = sc.nextLine();
            isValid = false;

            int trainerCount = trainers.size();

            while (!isValid) {
                System.out.print("choose your trainer : ");
                
                try {
                    ch = Integer.parseInt(sc.nextLine());
                    
                    if (ch > 0 && ch <= trainerCount) {
                        isValid = true;
                    } else {
                        System.out.printf("must be between 1 and %d. Try again." , trainerCount);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
            
            trainer= this.getTrainerByIndex(ch);

        }
        
        Member member = new Member(name , age , plan , trainer);

        this.addMember(member);
        System.out.println("Here your details :");
        System.out.println(this.getMemberByName(name).showMemberDetails());
    }

    public void createTrainer( Scanner sc) {

        System.out.println("Enter your name :");
        String name = sc.nextLine();
        System.out.println("Enter your age : ");
        int age = sc.nextInt();
        Trainer trainer = new Trainer(name , age);
        this.addTrainer(trainer);
        System.out.println("trainer added successfully..");
    }

    public Trainer getTrainerByValidName(Scanner sc){

        System.out.println("Enter your name :");
        String trainerName;
        trainerName = sc.nextLine();
        Trainer trainer;
        trainer = this.getTrainerByName(trainerName) ;
        while(trainer == null){
            System.out.println("enter valid name..");
            trainerName= sc.nextLine();
            trainer = this.getTrainerByName(trainerName) ;
        }
        return trainer;
    }

    public Member getMemberByValidName(Scanner sc){
        String memberName;
        System.out.println("Enter your name :");
        
        memberName = sc.nextLine();
        Member member;
        member = this.getMemberByName(memberName) ;
        while(member == null){
            System.out.println("enter valid name : ");
            memberName= sc.nextLine();
            member = this.getMemberByName(memberName) ;
        }
        return member;
    }

    public Trainer getTrainerByValidId(Scanner sc){

        System.out.println("Enter your id :");
        String trainerId;
        trainerId = sc.nextLine();
        Trainer trainer;
        trainer = this.getTrainerById(trainerId) ;
        while(trainer == null){
            System.out.println("enter valid id :");
            trainerId= sc.nextLine();
            trainer = this.getTrainerById(trainerId) ;
        }
        return trainer;
    }

    public Member getMemberByValidId(Scanner sc){
        String memberId;
        System.out.println("Enter your id :");
        
        memberId = sc.nextLine();
        Member member;
        member = this.getMemberById(memberId) ;
        while(member == null){
            System.out.println("enter valid id :");
            memberId= sc.nextLine();
            member = this.getMemberById(memberId) ;
        }
        return member;
    }

    public boolean takeAdminDetails(Scanner sc){
       
        String name ;
        String password ;
        System.out.println("Enter Admin name");
        name= sc.nextLine();
        while(!Main.isOnlyAphabets(name)) {
        	System.out.print("Enter only alphabets : ")
        	name= sc.nextLine();
        }
        
        System.out.println("Enter Admin password :");
        
        password= sc.nextLine();

        while(!this.getManagerName().equals(name) || !this.getManagerPassword().equals(password)){
            System.out.println("enter valid details..");
            System.out.println("Enter Admin name");
            name= sc.nextLine();
            System.out.println("Enter Admin password :");      
            password= sc.nextLine();
            
        };
        return true;
    }
}