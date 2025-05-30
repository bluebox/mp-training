import java.time.LocalDate;
import java.util.*;
public class Main {
    public static int enterAge() {
    	Scanner sc=new Scanner(System.in);
    	int age=0;
    	try {
    		age=sc.nextInt();
    		sc.nextLine();
    		return age;
    	}
    	catch(Exception e) {
    		System.out.println("enter a valid int age");
    		return enterAge();
    	}
    }
	public static void main(String[] args) {
		Gym bfc=new Gym();
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the no of Member to join the Gym");
		int n=sc.nextInt();
		sc.nextLine();
		for(int i=0;i<n;i++) {
			System.out.println("enter the details of "+(i+1)+" user ");
			System.out.println("enter name");
			String name=sc.nextLine();
			System.out.println("enter age");
			int age=enterAge();
			if(age<18 || age>100) {
				System.out.println("still a teen");
				continue;
			}
			String s=null;
			boolean temp=true;
			while(temp) {
				System.out.println("enter the required plan");
				System.out.println("enter B for Basic");
				System.out.println("enter G for Gold");
				System.out.println("enter P for Premium");
				s=sc.nextLine();
				s=s.toLowerCase();
//				System.out.println(s.charAt(0)+" is the character");
				switch(s.charAt(0)) {
				case 'b':
					s="basic";
					temp=false;
					break;
				case 'p':
					s="premium";
					temp=false;
					break;
				case 'g':
					s="gold";
					temp=false;
					break;
				default:
					temp=true;
					break;
				}
			}
				System.out.println("enter the duration in months");
				int duration=sc.nextInt();
				sc.nextLine();
				MemberShip a=new MemberShip(s,duration);
				Member b=new Member(name,age,a,LocalDate.now(),bfc.getSize()+1);
				bfc.addMember(b);
		}
        bfc.getAllMemberByJoiningDate();
//		System.out.println();
//		bfc.getByPlan("basic");
//		System.out.println();
//		bfc.getAllMember();
//	    bfc.deleteById(0);
//	    System.out.println();
//	    bfc.getAllMember();
//	    System.out.println();
//	    bfc.getByAge(22);
//		sc.close();
        bfc.deleteById(1);
        bfc.addMember(new Member("anand",22,new MemberShip("basic",9),LocalDate.now(),bfc.getSize()+1));
        bfc.getAllMemberByJoiningDate();
	}

}
