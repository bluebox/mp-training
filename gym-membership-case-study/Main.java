import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
        Gym gym=new Gym();
        
        while(true){
             System.out.println("enter the number \n (1)  add Members \n (2) assign membership plan to a member \n (3) view all Registered members \n (4) enter any character to exit\n");
            int choice;
            try{
                choice=sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException i){
                sc.nextLine();
                System.out.println("invalid input");
                break;
            }
            if(choice==1){
                
                System.out.println("Enter the details of member");
                System.out.println("Enter name of member");
                String name=sc.next();
                System.out.println("Enter the age");
                int age=sc.nextInt();
                
                
                System.out.println("Enter the memberid");
                int memberid=sc.nextInt();
                
                Member member=new Member(name, age, memberid);
                gym.Addmember(member);
                member.show_details();
            }
            else if(choice==2){
                System.out.println("Enter the Memberid");
                int memberid=sc.nextInt();
                
                System.out.println("Enter the planname");
                String planname=sc.next();
                System.out.println("Enter the plan duration");
                int planDuration=sc.nextInt();
                System.out.println("Enter the fee");
                int fee=sc.nextInt();
                
                MembershipPlan membershipplan=new MembershipPlan(planname, planDuration, fee);
                gym.assignPlan(memberid, membershipplan);

            }
            else if(choice==3){
                gym.show_All_Members();
            }
            else if(choice==4){
                break;
            }
            else{
                System.out.println("invalid number");
                continue;
            }
        }
    
}
}