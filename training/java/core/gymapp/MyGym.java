package gymapp;

public class MyGym implements Gym{

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