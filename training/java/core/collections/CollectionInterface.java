package collections;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
public class CollectionInterface {

	public static void main(String[] args) {
		Collection<Integer> nums = new ArrayList<Integer>();
		nums.add(22);
		nums.add(35);
		for(int i=1; i<5; i++) {
			nums.add((int) (Math.random() *100));
		}
		System.out.println(nums);
		nums.remove(35);
		System.out.println(nums.contains(22));
		System.out.println(nums.size());
		Iterator<Integer> values=nums.iterator();
		while(values.hasNext()) {
			System.out.println(values.next());
		}
		nums.clear();
		System.out.println(nums.isEmpty());
	}

}
