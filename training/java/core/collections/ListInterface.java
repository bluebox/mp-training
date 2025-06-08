package collections;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
public class ListInterface {

	public static void main(String[] args) {
		List <Integer> nums = new ArrayList<Integer>();
		nums.add(22);
		for(int i=1; i<5; i++) {
			nums.add((int) (Math.random() *100));
		}
		System.out.println(nums);
		System.out.println(nums.get(3));
		nums.set(3, 45);
		nums.add(2,35);
		System.out.println(nums);
		nums.remove(3);
		System.out.println(nums);
		nums.add(35);
		System.out.println(nums);
		nums.remove(Integer.valueOf(35));
		System.out.println(nums);
		System.out.println(nums.contains(57));		
		ListIterator<Integer>  val=nums.listIterator();
		while(val.hasNext()) {
			System.out.println(val.next());
		}
		while(val.hasPrevious()) {
			System.out.println(val.previous());
		}
		nums.sort((a,b)->(a-b));
		System.out.println(nums);
		nums.sort((a,b)->(b-a));
		System.out.println(nums);
	}

}
