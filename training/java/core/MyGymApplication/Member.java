package MyGymApplication;
import java.time.LocalDate;
public class Member extends Person {

    private String name;
    private int age;
    private String planTaken;
    private LocalDate joiningDate;
    private LocalDate endingDate;
    private String memberId;
    private String trainerName;

    static int membersCount = 10001;

    Member(){
        this("" );
    }

    Member(String name){
        this(name , -1 , "" , null);
    }

    Member(String name , int age ){
        this(name , age , "" , null);
    }

    Member(String name , int age , String planTaken ){
       this(name, age , planTaken , null);
    }

    Member(String name , int age , String planTaken , Trainer trainer){
        this.name = name;
        this.age = age;
        this.joiningDate = LocalDate.now();
        if(!planTaken.isEmpty())
            this.planTaken = planTaken;
            switch (planTaken) {
                case "basic" -> this.endingDate = this.joiningDate.plusMonths(1);
                case "gold" -> this.endingDate = this.joiningDate.plusDays(6);
                case "premium" -> this.endingDate = this.joiningDate.plusYears(1);
            }

        
        if(trainer!=null){
            trainer.addTrainee(this.name);
            this.trainerName = trainer.getName();
        }
         

        
        this.membersCount+=1;
        this.memberId = "befit" + this.membersCount;
    }

    public String getMemberId(){
        return this.memberId;
    }

    public String getName(){
        return this.name;
    }


    public String showMemberPlan(){
        return "Plan : " + planTaken + "";
    }

    public boolean isActive(){

        return true;
    }

    public String showMemberDetails(){
        String s = "Name : " + this.name + ",  age : " + this.age + 
        ",  id : " +  
        this. memberId+",  plan taken : "+ this.planTaken + 
        "\nDOJ : "+ this.joiningDate + ",  ending date : " + this.endingDate ;

        if (trainerName!=null && trainerName!=""){

         s = s + ",  trainer name : " + trainerName;
        }

        return s + "\n----------------------------------------------------------------------";

    }

    public String toString(){
        return "Member Name : %s, Id : %s, age : %d\n, plan : %s, DOJ : %s,ending date : %s, trainer : %s".formatted(this.name , this.memberId ,this.age , this.planTaken , this.joiningDate ,this.endingDate , this.trainerName);
    }

}
