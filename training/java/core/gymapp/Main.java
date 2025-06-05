package gym_application;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.HashMap;

interface Gym{

    void gymMemberDetails();

    void gymMembershipPlans();

    void gymTrainerDetails();

}


class MyGym implements Gym{

    private String gym_name = "beFit gym";
    private String gymLocation = "Hyderabad";
    private String manager_name = "naga";
    private String managerId = "befit10001";
    private String managerPassword = "naga";
    static ArrayList<Member> members;
    static ArrayList<Trainer> trainers;

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
        return this.manager_name;
    }
    public String getManagerPassword(){
        return this.managerPassword;
    }

    public void addMember(Member obj){
        members.add(obj);
    }

    public void removeMember(Member obj){
        if( members.remove(obj) ) Member.members_count--;
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
        System.out.println("Gym name : "+ this.gym_name + " location : " + this.gymLocation);
    }

    public void gymManagerDetails(){
        System.out.println("-".repeat(20));
        System.out.println("GYM MANAGER DETAILS");
        System.out.println("Manager name : "+this.manager_name + " manager id : "+ this.managerId);
    }

    public void gymMemberDetails(){
        System.out.println("-".repeat(20));
        System.out.println("GYM MEMBERS DETAILS");
        for(Member mem : members){
            System.out.println(mem.showMemberDetails());
        }
    }

    public void gymMemberNames(){
        System.out.println("-".repeat(20));
        System.out.println("GYM MEMBERS NAMES");
        for(Member mem : members){
            System.out.println(mem.name);
        }
    }


    public static Trainer getTrainerByName(String name){
        
        for(Trainer obj: trainers){
            if(obj.name.equalsIgnoreCase(name)){
                return obj;
            }
        }
        return null;
    }

    public static Trainer getTrainerById(String id){
        
        for(Trainer obj: trainers){
            if(obj.trainerId.equals(id)){
                return obj;
            }
        }
        return null;
    }

    public static Member getMemberByName(String name){
        
        for(Member obj: members){
            if(obj.name.equalsIgnoreCase(name)){
                return obj;
                
            }
        }
        return null;
    }

    public static Member getMemberById(String id){
        
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
        for(Trainer trainer : trainers){
            trainer.details();
        }
    }

    public void gymTrainerNames(){
        System.out.println("-".repeat(20));
        System.out.println("GYM TRAINERS :");
        int i = 1;
        for(Trainer trainer : trainers){
           System.out.println(i +": " +trainer.name);
           i++;
        }
    }

    public String getTrainerByIndex(int idx){
       return trainers.get(idx - 1).name;    
    }
    
    public void gymMembershipPlans(){
        MembershipPlan.showPlans();
    }
}


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

                                String choice = sc.nextLine();

                                switch (choice) {
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
                    System.out.println("choose your operation : (q -> quit)");
                    System.out.println("""
                            1.Get your details by name
                            2.Get your details by id
                            """);
                    String choice = sc.nextLine();

                    switch(choice){
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
                    String op = sc.nextLine();

                    switch(op){

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
