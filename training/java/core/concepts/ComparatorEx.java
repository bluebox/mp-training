package concepts;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ComparatorEx {

	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<>();
		nums.add(45);
		nums.add(22);
		nums.add(57);
		nums.add(5);
		nums.add(16);
		System.out.println("Before sorting:"+nums);
		List<Integer> nums2=nums;
		Collections.sort(nums);
		System.out.println("After sorting:"+nums);
		Comparator<Integer> com = new Comparator<Integer>() {
			public int compare(Integer i, Integer j) {
				if(i%10>j%10) return 1;
				else return -1;
			}
		};
		System.out.println("Before sorting with last digit:"+nums2);
		Collections.sort(nums2, com);
		System.out.println("After sorting with last digit:"+nums2);
		
		List<String> names = new ArrayList<>();
		names.add("Laxman");
		names.add("Bharat");
		names.add("Shatragna");
		names.add("Ram");
		names.add("Ravan");
		names.add("Balram");
		System.out.println("Before sorting on length of the string:"+names);
		Comparator<String> comp = new Comparator<String>() {
			public int compare(String str1, String str2) {
				if(str1.length()>str2.length()) return 1;
				else return -1;
			}
		};
		Collections.sort(names, comp);
		System.out.println("After sorting on length of the string:"+names);
		
	}

}
