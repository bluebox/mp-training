package collections;
import java.util.LinkedHashSet;
import java.util.Set;
public class LinkedHashSetInterface {

	public static void main(String[] args) {
		Set<Integer> nums = new LinkedHashSet<Integer>();
		nums.add(45);
		nums.add(57);
		nums.add(22);
		nums.add(35);
		System.out.println(nums);
		for(int i=1; i<=10; i++) {
			if(i%2==0) nums.add(i+1);
			else nums.add(i);
		}
		System.out.println(nums);
		nums.remove(11);
		System.out.println(nums);

	}

}
