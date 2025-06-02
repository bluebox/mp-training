package gym_application;

public class Member extends Person {

    String name;
    private int age;
    private String planTaken;
    private String joiningDate;
    private String endingDate;
    private String memberId;
    private String trainer_name;

    static int members_count = 10001;

    Member(){
        this("" );
    }

    Member(String name){
        this(name , -1 , "" , "");
    }

    Member(String name , int age ){
        this(name , age , "" , "");
    }

    Member(String name , int age , String planTaken ){
       this(name, age , planTaken , "");
    }

    Member(String name , int age , String planTaken , String trianer_name){
        this.name = name;
        this.age = age;

        if(!planTaken.isEmpty())
            this.planTaken = planTaken;

        if(!trianer_name.isEmpty()){
            this.trainer_name = trianer_name;
            MyGym.getTrainerByName(trainer_name).trainee.add(this.name);
            
        }
         

        this.joiningDate = Main.getTodayDate();
        this.members_count+=1;
        this.memberId = "befit" + this.members_count;
    }

    public String getMemberId(){
        return this.memberId;
    }


    public String showMemberPlan(){
        return "Plan : " + planTaken + "";
    }

    public boolean isActive(){

        return true;
    }

    public String showMemberDetails(){
        String s = "Name : " + this.name + ", age : " + this.age + 
        ", id : " +  
        this. memberId+", plan taken : "+ this.planTaken + 
        ", DOJ : "+ this.joiningDate;

        if (trainer_name!=null && trainer_name!=""){

         s = s + ", trainer name : " + trainer_name;
        }

        return s + "\n------------------------";

    }

}
