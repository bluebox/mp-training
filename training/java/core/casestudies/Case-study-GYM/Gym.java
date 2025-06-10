import java.util.*;

class Gym {
    private List<Member> members;
    private List<Plan> plans;
    Scanner sc= new Scanner(System.in);
    public Gym(){
        members= new ArrayList<>();
        plans= new ArrayList<>();
        plans.add(new BasicPlan(1));
        plans.add(new GoldPlan(1));
        plans.add(new PremiumPlan(1));
        members.add(new Member("SRISAI",22,"MALE",101,"gold",2));
        members.add(new Member("Ganesh",23,"MALE",102,"Premium",3));
        members.add(new Member("Rahul",25,"MALE",103,"basic",1));
        members.add(new Member("Rashmitha",28,"Female",104,"gold",3));
        // members.add(new Member("SRISAI",22,"MALE",101,"gold",2));
    }

    public void showAllMembers(){        
        for(Member m: members){
            printDetails(m);            
        }
        if(members.size()==0){
            System.out.println("no Gym Members");
        }
    }
    public void addMember(){
        System.out.println("Enter Name : ");
        String name=sc.nextLine();
        System.out.println("Enter age : ");                    
        int age= Integer.parseInt(sc.nextLine());
        System.out.println("Enter your Plan : ");
        String plan=sc.nextLine();
        System.out.println("Enter your Gender : ");
        String gender=sc.nextLine();
        System.out.println("Enter your ID : ");
        int id=Integer.parseInt(sc.nextLine());
        System.out.println("Enter Duration : ");
        int duration=Integer.parseInt(sc.nextLine());
        Member mem= new Member(name,age,gender,id,plan,duration);
        members.add(mem);
        System.out.println("Member Added Successfully....");
    }
    public void removeMember(int id){
        int idx=0;
        for(Member m:members){
            if(m.getGymId()==id){
                members.remove(idx);
                System.out.println(id +" removed Successfully !");
                return;
            }
            idx++;
        }
        System.out.println("Enter Valid Id");
    }   

    public void getDetails(int id){
        boolean status=true;
        for(Member m:members){
            if(m.getGymId()==id){
                printDetails(m);status=false;
            }
        }
        if(status){
            System.out.println("No Details Exists!!. Enter Correct Details");
        }
    }
    public void getDetails(String name){
         boolean status=true;
        for(Member m:members){           
            if((m.getName()).equalsIgnoreCase(name)){
                printDetails(m);status=false;
            }            
        }
        if(status){
            System.out.println("No Details Exists!!. Enter Correct Details");
        }
    }
    public void printDetails(Member m){
        System.out.println();
        System.out.println("Name  : "+m.getName());
        System.out.println("GymId : "+m.getGymId());
        System.out.println("Age : "+m.getAge());
        System.out.println("Duration : "+m.getDuration()+" Months");
        System.out.println("Plan : "+m.getPlan());
        System.out.println("Plan Cost per Month : "+m.getPlanCost());
        System.out.println("Total Fee : "+m.getFee());
        System.out.println();

    }
    public void showPlans(){
        for(Plan plan:plans){
            System.out.println();
            System.out.println("-----".repeat(10));
            System.out.println("Plan name   : "+plan.getPlanName());
            System.out.println("Duration    : "+plan.getDuration());
            System.out.println("Monthly Fee : "+plan.getMonthlyfee()+"\n");
            System.out.println("--FEATURES--\n"+plan.getFeatures());
            System.out.println();
            System.out.println("-----".repeat(10));
        }
    }

    public void showPlan(String pname){
        for(Plan plan:plans){
            if(plan.getPlanName().equalsIgnoreCase(pname)){
                System.out.println();
                System.out.println("-----".repeat(10));
                System.out.println("Plan name   : "+plan.getPlanName());
                System.out.println("Duration    : "+plan.getDuration());
                System.out.println("Monthly Fee : "+plan.getMonthlyfee()+"\n");
                System.out.println("--FEATURES--\n"+plan.getFeatures());
                System.out.println();
                System.out.println("-----".repeat(10));
            }           
        }
    }
    
}