package collections;
import java.util.TreeSet;
import java.util.Set;
public class TreeSetInterface {
	public static void main(String[] args) {
		Set<Integer> nums = new TreeSet<Integer>();
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
		System.out.println(((TreeSet<Integer>) nums).first());
		System.out.println(((TreeSet<Integer>) nums).last());
		System.out.println(((TreeSet<Integer>) nums).ceiling(25));
		System.out.println(((TreeSet<Integer>) nums).floor(25));
	}

}
