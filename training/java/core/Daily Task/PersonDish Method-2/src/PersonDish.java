import java.util.*;

public class PersonDish {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		HashMap<Integer,Integer> person=new HashMap<>();
		person.put(0,0);
		person.put(1,0);
		int count=sc.nextInt();
		System.out.println("Enter the type of person");
		for(int i=0;i<count;i++) {
			if(sc.nextInt()==0) {
				person.put(0, person.get(0)+1);
			}
			else {
				person.put(1, person.get(1)+1);
			}
		}
		System.out.println("Person"+person);
		System.out.println("Enter the type of plate:");
		ArrayList<Integer> plate=new ArrayList<>();
		for(int i=0;i<count;i++) {
			plate.add(sc.nextInt());
		}
		System.out.println("Plate"+plate);
		for(int i:plate) {
			if(person.get(i)>0) {
				person.put(i, person.get(i)-1);
				count-=1;
			}
			else {
				break;
			}
		}
		System.out.println("No of people left starving are: "+count);
		sc.close();
	}
}
