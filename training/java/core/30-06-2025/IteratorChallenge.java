import java.util.*;
public class IteratorChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		Iterator<Integer> iterate=list.iterator();
		while(iterate.hasNext())
		{
			int number=iterate.next();
			System.out.println(number);
			iterate.remove();
			System.out.println("removed element "+number);
		}
		
		while(iterate.hasNext())
		{
			System.out.println(iterate.next());
		}
		
		System.out.println("Final list is "+list);		

	}

}
