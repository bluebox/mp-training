package gymapp;
import java.util.ArrayList;

public class Trainer extends Person{
    String name;
    int age;
    String trainerId;
    protected ArrayList<String> trainee;
    static int trainers_count = 0;

    Trainer(){
        this("unknown" , -1);
        
    }

    Trainer(String name){
        this(name , -1);
    }

    Trainer(String name , int age){
        this.name = name;
        this.age = age;
        trainee = new ArrayList<>();
        Member.members_count++;
        this.trainerId ="befit"+Member.members_count;

    }
    void details(){
        // System.out.println("-".repeat(20));
        System.out.println("Name : "+ 
        this.name +",  age : "+ this.age + ",  id : " + trainerId);
    }

    public void showTrainees(){
        System.out.println("Trainer : " + this.name);
        System.out.println("Trainee List : ");
        trainee.forEach(System.out::println);
        System.out.println("---------------");
    }



}