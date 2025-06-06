package MyGymApplication;
import java.util.ArrayList;

public class Trainer extends Person{

    private String name;
    private int age;
    private String trainerId;
    private ArrayList<String> trainee;

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
        Member.membersCount++;
        this.trainerId ="befit"+Member.membersCount;

    }

    public void addTrainee(String name){
        trainee.add(name);
    }

    public String getName(){
        return this.name;
    }

    public String getTrainerId(){
        return this.trainerId;
    }

    public int getAge(){
        return this.age;
    }

    void details(){
        // System.out.println("-".repeat(20));
        System.out.println("Name : "+ 
        this.name +",  age : "+ this.age + ",  id : " + trainerId);
    }

    public void showTrainees(){
        System.out.println("Trainer : " + this.name);
        if(trainee.size() > 0){
            System.out.println("Trainee List : ");
        
            trainee.forEach(System.out::println);
        }
        else{
            System.out.println("you have no trainee's");
        }
        

        System.out.println("---------------");
    }

    public String toString(){
        return "Trainer Name : %s, Id : %s, age : %d".formatted(this.name , this.trainerId ,this.age );
    }

}