import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gym gym= new Gym();        
        Scanner sc= new Scanner(System.in);
        String str="";
            System.out.println();
            System.out.println("---OPTIONS---");
            System.out.println("To see Plans Enter 1");
            System.out.println("To add Member Enter 2");
            System.out.println("To fetch Member details by Name Enter 3");
            System.out.println("To fetch Member details by ID Enter 4");
            System.out.println("To exit the membership Enter 5");
            System.out.println("To fetch All Gym Members Enter 6");
            System.out.println("Enter Q to exit the program:");
        do{

           System.out.println("ENTER input"); 
            str=sc.nextLine();
            System.out.println();
            switch(str){                
                case "1":gym.showPlans();break;
                case "2":{                    
                    gym.addMember();
                    break;
                }
                case "3":{
                    System.out.println("Enter the name to fetch");
                    String name=sc.nextLine();
                    gym.getDetails(name);
                    break;
                }
                case "4":{
                    System.out.println("Enter the ID to fetch");
                    int id= sc.nextInt();
                    gym.getDetails(id);
                    break;
                }
                case "6":{
                            gym.showAllMembers();
                            break;
                }
                case "5":{
                    System.out.println("Enter your Id to Close the membership");
                    int id=sc.nextInt();
                    gym.removeMember(id);
                    break;
                }                
                case "Q","q":{
                    System.out.println("*** PROGRAM CLOSED ***");
                    break;
                }
                default:    {System.out.println("wrong input");                               
                          break;}
                
            }
        }while(!str.equalsIgnoreCase("q"));        
    }
}
