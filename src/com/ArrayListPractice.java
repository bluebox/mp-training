import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayListPractice {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>(12);
		list.add(12);
		list.addAll(new ArrayList<Integer>(Arrays.asList(1,2,34,4)));
		System.out.println(list);
		System.out.println(list.size());
		for(int i:list) {
			System.out.println(i);
		}
	}
}
