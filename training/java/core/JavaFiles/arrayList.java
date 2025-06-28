
import java.util.ArrayList;


public class arrayList {

	public static void main(String[] args) {
		ArrayList<Integer> arr=new ArrayList<> ();
		
		System.out.println(arr);
		for(int i=0;i<10;i++) {
			arr.add(i);			
		}
		System.out.println(arr);
		arr.add(10, 10);
		System.out.println(arr);
		System.out.println(arr.get(9));
		arr.set(5, 55);
		System.out.println(arr);
		
		System.out.println(arr.get(5));
		arr.remove(5);
		System.out.println(arr);
		

	}

}
