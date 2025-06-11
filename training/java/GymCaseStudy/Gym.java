package GymCaseStudy;


import java.util.ArrayList;

public class Gym{
    private String gymName = "SLIM FIT";
    private String gymLocation = "HYDERBAD";
    private String managerName = "Durga Prasad";
    private String managerId = "SF1096642";
    private String managerPassword = "DP123#";
    static ArrayList<Members> members;
    static ArrayList<Trainers> trainers;
    public  Gym() {

        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    public  boolean checkLogin(String id,String password) {
        if( (id.equals("SF1096642")) && (password.equals("DP123#"))){ return true; }
        return false;

    }
    public String getName() {
        return "DP";
    }
    public void membersDetails() {
        for(Members member:members){
            System.out.println("Name: "+member.getName());
            System.out.println("Age: "+member.getAge());
            System.out.println("Weight: "+member.getWeight());
            System.out.println("Address :"+member.getAddress());
            System.out.println("PlanType:"+member.getPlanType());
            System.out.println("StartDate: "+member.getStartDate());
            System.out.println("Trainer: "+member.getTrainer());
            System.out.println("-".repeat(30));
        }

    }

    public void trainersDetails() {
        for(Trainers trainer:trainers){
            System.out.println("Name: "+trainer.getName());
            System.out.println("Age: "+trainer.getAge());
            System.out.println("Experiances: "+trainer.getExperiance());
            System.out.println("alocated coustomers are:");
            trainer.assignedCoustomers.forEach(System.out::println);
            System.out.println("-".repeat(30));
        }
    }
    public void addCoustomer(Members member,String trainee){
        for(Trainers trainer:trainers){
            if(trainer.getName().equals(trainee)){

                trainer.allocateCostomer(member);
            }
        }

    }
    public void displayTrainers(){
        System.out.println("-".repeat(30));
        for(Trainers trainer:trainers){
            System.out.println("Name: "+trainer.getName()+","+"Age: "+trainer.getAge()+","+"Experiances: "+trainer.getExperiance());
        }
        System.out.println("-".repeat(30));

    }
    public void addUser(Members member) {
        this.members.add(member);
    }
    public void addTrainer(Trainers trainer) {
        trainers.add(trainer);
    }
}


