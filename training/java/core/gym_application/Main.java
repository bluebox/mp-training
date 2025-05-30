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

}

abstract class Person{

    private String name;
    private int age;



    void details(){
        System.out.println("Name : "+ this.name +" age : "+ this.age);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}

class Member extends Person{

    private String name;
    private int age;
    String planTaken;
    String joiningDate;
    String endingDate;
    String memberId;

    static int members_count = 1;

    Member(){
        this("" );
    }

    Member(String name){
        this.name = name;
    }

    Member(String name , int age ){
        this.name = name;
        this.age = age;
    }

    Member(String name , int age , String planTaken ){
        this.name = name;
        this.age = age;
        this.planTaken = planTaken;
        this.joiningDate = Main.getTodayDate();
        this.members_count+=1;
        this.memberId = "befit_" + this.members_count;
    }



    public String showMemberPlan(){
        return "Plan : " + planTaken + "";
    }

    public boolean isActive(){
        return true;
    }

    public String showMemberDetails(){
        return "Name : " + this.name + "\nage : " + this.age + 
        "\nid : " +  
        this. memberId+"\nplan taken : "+ this.planTaken + 
        "\nDate of joining : "+ this.joiningDate + "\n-------------------------------------";


    }

}

class Trainer extends Person{
    private String name;
    private int age;

    Trainer(){
        this.name = "unknown";
        this.age = -1;

    }

    Trainer(String name){
        this.name = name;
        this.age = -1;
    }

    Trainer(String name , int age){
        this.name = name;
        this.age = age;
    }



}

class MyGym implements Gym{

    private String gym_name = "beFit gym";
    private String gymLocation = "Hyderabad";
    private String manager_name = "nagabhushan";
    private String managerId = "befit_1";
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
        System.out.println("Gym name : "+ this.gym_name + " location : " + this.gymLocation);
    }

    public void gymManagerDetails(){
        System.out.println("Manager name : "+this.manager_name + " manager id : "+ this.managerId);
    }

    public void gymMemberDetails(){
        for(Member mem : members){
            System.out.println(mem.showMemberDetails());
        }
    }

    public void gymTrainerDetails(){
        for(Trainer trainer : trainers){
            trainer.details();
        }
    }

    public void gymMembershipPlans(){
        MembershipPlan.showPlans();
    }
}
class MembershipPlan{
    public static void showPlans(){
        System.out.println("Basic Plan -> cost : 1000 rs per month \nGold Plan -> 800 per month 6 months aggrement\n Premium Plan");
    }

    static String[] plans = {"basic" , "gold" , "premium"};

    
}
public class Main {

    public static String getTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Member member1 = new Member("reddysekhar" , 23 , "basic");
        Member member2 = new Member("saketh" , 23 , "gold");
        Member member3 = new Member("venu" , 24 , "premium");

        

        MyGym beFit = new MyGym();
        
        beFit.gymManagerDetails();
        beFit.gymDetails();
        beFit.addMember(member1);
        beFit.addMember(member2);
        beFit.addMember(member3);
        beFit.gymMemberDetails();
        beFit.removeMember(member3);
        beFit.gymMemberDetails();
        beFit.addMember(member3);
        beFit.gymMemberDetails();
        //System.out.println("Choose your plan");
        

    }
}
