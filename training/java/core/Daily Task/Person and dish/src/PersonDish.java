import java.util.*;
public class PersonDish {
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		Queue<Integer> person=new LinkedList<>();
		ArrayList<Integer> plate=new ArrayList<>();
		System.out.print("No of persons/plates: ");
		int count=sc.nextInt();
		for(int i=0;i<count;i++) {
			System.out.print("The type of person is: ");
			person.add(sc.nextInt());
			System.out.print("The type of plate is: ");
			plate.add(sc.nextInt());
		}
		System.out.println("Person order is: "+person);
		System.out.println("Plate order is: "+plate);
		int size=0;
		while(!person.isEmpty()) {
			int x=person.poll();
			if(x==plate.get(0)) {
				plate.remove(0);
				size=0;
				count-=1;
			}
			else {
				person.add(x);
				size+=1;
				if(count==size) {
					break;
				}
			}
		}
		System.out.println("No  of people left starving are: "+count);
	}
}
