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

    public static String getTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    public static void createMember(MyGym beFit , Scanner sc){

        
        
        System.out.println("Enter your details");
        System.out.println("Name :");
        String name = sc.nextLine();
        System.out.println("Enter age :");
        int age = sc.nextInt();
        sc.nextLine();
        beFit.gymMembershipPlans();
        
        System.out.println("Choose Your Plan");
        String ch = sc.nextLine() ;
        // sc.nextLine();
        String plan = "";

        switch(ch){
            case "1" -> {System.out.println("You have selected base plan"); plan="basic";}
            
            case "2" -> {System.out.println("You have selected gold plan");plan="gold";}

            case "3" -> {System.out.println("You have selected premium plan");plan="premium";}
        }

        System.out.println("Do you need trainer (additional cast -> 500/month) (Y/N) :");

        ch = sc.nextLine();
        
        String trainer_name = "";

        if("yY".contains(ch)){
            System.out.println("choose your trainer (enter no.) :");
            beFit.gymTrainerNames();
            ch = sc.nextLine();
            
            trainer_name= beFit.getTrainerByIndex(Integer.parseInt(ch));

        }
        
        Member member = new Member(name , age , plan , trainer_name);

        beFit.addMember(member);
        System.out.println("Here your details :");
        System.out.println(beFit.getMemberByName(name).showMemberDetails());
    }

    public static void createTrainer(MyGym beFit , Scanner sc) {
        System.out.println("Enter your name :");
        String name = sc.nextLine();
        System.out.println("Enter your age : ");
        int age = sc.nextInt();
        Trainer trainer = new Trainer(name , age);
        beFit.addTrainer(trainer);
        System.out.println("trainer added successfully..");
    }

    public static boolean takeAdminDetails(MyGym beFit , Scanner sc){
       
        String name ;
        String password ;
        System.out.println("Enter Admin name");
        name= sc.nextLine();
        System.out.println("Enter Admin password :");
        
        password= sc.nextLine();

        while(!beFit.getManagerName().equals(name) || !beFit.getManagerPassword().equals(password)){
            System.out.println("enter valid details..");
            System.out.println("Enter Admin name");
            name= sc.nextLine();
            System.out.println("Enter Admin password :");      
            password= sc.nextLine();
            
        };
        return true;
    }

    public static boolean authAdmin(MyGym beFit , String name , String password ){

        while(!(beFit.getManagerName().equals(name) && beFit.getManagerPassword().equals(password))){
            System.out.println("enter valid details..");
            Scanner sc = new Scanner(System.in);
            takeAdminDetails(beFit, sc);
        };
        return true;

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

        Member member1 = new Member("reddysekhar" , 23 , "basic" , "venkat");
        Member member2 = new Member("saketh" , 23 , "gold" );
        Member member3 = new Member("venu" , 24 , "premium" , "raju");


        
        beFit.addMember(member1);
        beFit.addMember(member2);
        beFit.addMember(member3);

        //System.out.println("------------------------------");

        boolean looping = true;
        while (looping){

            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Welcome to Befit Gym");
            System.out.println("1.Admin    2.Existing Member    3.New Member Registration   4.Trainer   q.Quit");

            String ch = sc.nextLine();

            switch(ch){
                
                case "1" -> {
                    if(takeAdminDetails(beFit, sc)){
                        boolean inLoop = true;

                        while(inLoop){
                            System.out.println("choose your operation : (q -> quit)");
                            System.out.println("""

                                    1.Add a user
                                    2.Add a trainer
                                    3.Remove a user by name
                                    4.Remove a user by id
                                    5.Remove a trainer by name
                                    6.Remove a trainer by id
                                    7.Get user details by name
                                    8.Get user details by id
                                    9.Get trainer details by name
                                    10.Get trainer details by id
                                    11.Get all users details
                                    12.Get all trainer details
                                    13.Get trainee list of trainer
                                    """);

                                String choice = sc.nextLine();

                                switch (choice) {
                                    case "1" -> {
                                        createMember(beFit, sc);
                                    }

                                    case "2" -> {
                                        createTrainer(beFit, sc);
                                    }
                                    case "3" -> {
                                        System.out.println("Enter your name :");
                                        String user_name;
                                        user_name= sc.nextLine();

                                        while(beFit.getMemberByName(user_name) == null){
                                            System.out.println("enter valid name..");
                                            user_name= sc.nextLine();
                                        }
                                        beFit.removeMember(beFit.getMemberByName(user_name));
                                        System.out.println(user_name + " removed successfully..\n----------------------------");
                                    }

                                    case "4" -> {
                                        System.out.println("Enter your id :");
                                        String user_id ;
                                        user_id= sc.nextLine();
                                        while(beFit.getMemberById(user_id) == null){
                                            System.out.println("enter valid id..");
                                            user_id= sc.nextLine();
                                        }
                                        beFit.removeMember(beFit.getMemberById(user_id));
                                        System.out.println(user_id + " removed successfully");

                                    }
                                    case "5" -> {
                                        System.out.println("Enter your name :");
                                        String trainer_name;
                                        trainer_name = sc.nextLine();

                                        while(beFit.getTrainerByName(trainer_name) == null){
                                            System.out.println("enter valid name..");
                                            trainer_name= sc.nextLine();
                                        }
                                        beFit.removeTrainer(beFit.getTrainerByName(trainer_name));
                                        System.out.println(trainer_name + " removed successfully..\n----------------------------");
                                        
                                    }

                                    case "6" -> {
                                        System.out.println("Enter your id :");
                                        String trainer_id;
                                        trainer_id = sc.nextLine();
                                        while(beFit.getTrainerById(trainer_id) == null){
                                            System.out.println("enter valid id..");
                                            trainer_id= sc.nextLine();
                                        }
                                        beFit.removeTrainer(beFit.getTrainerById(trainer_id));
                                        System.out.println(trainer_id + " removed successfully..");

                                    }
                                    case "7" -> {
                                        System.out.println("Enter your name :");
                                        String user_name;
                                        user_name= sc.nextLine();

                                        while(beFit.getMemberByName(user_name) == null){
                                            System.out.println("enter valid name..");
                                            user_name= sc.nextLine();
                                        }
                                        System.out.println(beFit.getMemberByName(user_name).showMemberDetails());
                                    }

                                    case "8" -> {
                                        System.out.println("Enter your id :");
                                        String user_id ;
                                        user_id= sc.nextLine();
                                        while(beFit.getMemberById(user_id) == null){
                                            System.out.println("enter valid id..");
                                            user_id= sc.nextLine();
                                        }
                                        System.out.println(beFit.getMemberById(user_id).showMemberDetails());
                                    }
                                    case "9" -> {
                                        System.out.println("Enter your name :");
                                        String trainer_name;
                                        trainer_name = sc.nextLine();

                                        while(beFit.getTrainerByName(trainer_name) == null){
                                            System.out.println("enter valid name..");
                                            trainer_name= sc.nextLine();
                                        }
                                        beFit.getTrainerByName(trainer_name).details();
                                    }

                                    case "10" -> {
                                        System.out.println("Enter your id :");
                                        String trainer_id;
                                        trainer_id = sc.nextLine();
                                        while(beFit.getTrainerById(trainer_id) == null){
                                            System.out.println("enter valid id..");
                                            trainer_id= sc.nextLine();
                                        }
                                        beFit.getTrainerById(trainer_id).details();
                                    }
                                    case "11" -> {
                                        beFit.gymMemberDetails();
                                    }

                                    case "12" -> {
                                        beFit.gymTrainerDetails();
                                        System.out.println("-------------------------------");
                                    }
                                    case "13" -> {

                                        System.out.println("Enter your name :");
                                        String trainer_name;
                                        trainer_name = sc.nextLine();

                                        while(beFit.getTrainerByName(trainer_name) == null){
                                            System.out.println("enter valid name..");
                                            trainer_name= sc.nextLine();
                                        }
                                        beFit.getTrainerByName(trainer_name).showTrainees();

                                    }
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
                        case "1" -> {
                            System.out.println("Enter your id :");
                            String user_name ;
                            user_name= sc.nextLine();
                            while(beFit.getMemberByName(user_name) == null){
                                System.out.println("enter valid id..");
                                user_name= sc.nextLine();
                            }
                            System.out.println( beFit.getMemberByName(user_name).showMemberDetails());
                        }

                        case "2" -> {
                            System.out.println("Enter your id :");
                            String user_id ;
                            user_id= sc.nextLine();
                            while(beFit.getMemberById(user_id) == null){
                                System.out.println("enter valid id..");
                                user_id= sc.nextLine();
                            }
                            System.out.println( beFit.getMemberById(user_id).showMemberDetails());
                        }
                    
                    }
                    
                }
                
                case "3" -> {
                    createMember(beFit, sc);
                }

                case "4" -> {
                    System.out.println("""
                            1.get your details by name
                            2.get your details by id
                            3.show your trainee list
                            """);
                    String op = sc.nextLine();

                    switch(op){

                        case "1" -> {
                            System.out.println("Enter your name :");
                            String trainer_name;
                            trainer_name = sc.nextLine();

                            while(beFit.getTrainerByName(trainer_name) == null){
                                System.out.println("enter valid name..");
                                trainer_name= sc.nextLine();
                            }
                            beFit.getTrainerByName(trainer_name).details();
                        }

                        case "2" -> {
                            System.out.println("Enter your id :");
                            String trainer_id;
                            trainer_id = sc.nextLine();
                            while(beFit.getTrainerById(trainer_id) == null){
                                System.out.println("enter valid id..");
                                trainer_id= sc.nextLine();
                            }
                            beFit.getTrainerById(trainer_id).details();
                        }

                        case "3" -> {

                            System.out.println("Enter your name :");
                            String trainer_name;
                            trainer_name = sc.nextLine();

                            while(beFit.getTrainerByName(trainer_name) == null){
                                System.out.println("enter valid name..");
                                trainer_name= sc.nextLine();
                            }
                            beFit.getTrainerByName(trainer_name).showTrainees();

                        }


                    }
                }

                
                case "q" ->{
                    looping = false;
                    break;
                }

                case "Q" -> {
                    looping = false;
                    break;
                }

                default -> {
                    System.out.println("Enter valid option (1 or 2 or 3 or 4)");
                }

            }

        }
        
    }
}
