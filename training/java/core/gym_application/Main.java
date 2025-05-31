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
    private String manager_name = "nagabhushan";
    private String managerId = "beFit_1";
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
            if(obj.name.equals(name)){
                return obj;
            }
        }
        return null;
    }

    public static Member getMemberByName(String name){
        
        for(Member obj: members){
            if(obj.name.equals(name)){
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

        // beFit.gymManagerDetails();
        // beFit.gymDetails();
        
        beFit.addMember(member1);
        beFit.addMember(member2);
        beFit.addMember(member3);

        //beFit.getMemberByName("venu").showMemberDetails();
        // beFit.gymTrainerDetails();
        // beFit.gymMemberDetails();
        System.out.println("------------------------------");
        // trainer1.showTrainees();

        // beFit.gymMemberNames();
        // beFit.gymTrainerNames();
       // System.out.println("Choose your plan");

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

        System.out.println("Do you need trainer (Y/N)");

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
}
