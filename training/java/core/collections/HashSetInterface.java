package collections;
import java.util.HashSet;
import java.util.Set;
public class HashSetInterface {

	public static void main(String[] args) {
		Set<Integer> nums = new HashSet<Integer>();
		nums.add(45);
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
