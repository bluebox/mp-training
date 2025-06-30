import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {

	public static void main(String[] args) {
		ArrayList<Integer> l =new ArrayList<>();
		
		System.out.println(l);
		
		for(int i=0;i<10;i++) {
			l.add((i+1)*2);
		}
		System.out.println(l);
		
		l.add(1,50);
		System.out.println(l);
		
		l.set(2, 500);
		System.out.println(l);

		l.remove(0);
		System.out.println(l);
		
		List<Integer> lis=new ArrayList<>();
		lis.add(1000);
		lis.add(2000);
		
		l.addAll(lis);
		System.out.println(l);
		
		System.out.println(l.get(10));
		System.out.println(l.contains(2000));
		System.out.println(l.contains(20000));
		System.out.println(l.indexOf(1000));
		l.add(1,1000);
		System.out.println(l);

		System.out.println(l.lastIndexOf(1000));
		System.out.println(l.lastIndexOf(1000));

		l.remove(0);
		System.out.println(l);
		
		l.removeAll(lis);
		System.out.println(l);

	}

}
