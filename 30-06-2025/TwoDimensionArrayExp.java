import java.util.*;

public class TwoDimensionArrayExp {

	public static void main(String args[]) {
		
		int s1[] = new int[] {1,3,5,7,9};
		int s2[] = new int[] {2,4,6,8};
		
		if(Arrays.equals(s1, s2)) {
			System.out.println("s1 and s2 are equal");
		}
		else {
			System.out.println("s1 and s2 are not equal");
		}
		
		System.out.println();
		
		int[] a = new int[5];
		System.out.println(Arrays.toString(a));
		
		a[3] = 33;
		System.out.println(Arrays.toString(a));
		
		System.out.println(a.length);
		
		int[][] array = new int[3][3];
		System.out.println(Arrays.deepToString(array));
		
		
		
		if(Arrays.equals(s1, a)) {
			System.out.println("s1 and a are equal");
		}
		else {
			System.out.println("s1 and a are not equal");
		}
		
		
		String[] sArray = {"Abby" , "Bob" , "Catty" ,"Elf","David"};
		Arrays.sort(sArray);
		System.out.println(Arrays.toString(sArray));
		
		ArrayList<String> al = new ArrayList<>();
		al.add("Blue");
		al.add("Yellow");
		al.add("Red");
		al.add("Pink");
		
		System.out.println(al);
		
		al.sort(Comparator.naturalOrder());
		
		System.out.println(al);
		
		al.sort(Comparator.reverseOrder());
		
		System.out.println(al);
		
		al.remove(0);
		
		System.out.println(al);
		
	    if(al.contains(sArray)) {
	    	System.out.println("Found it.....");
	    }
	    else {
	    	System.out.println("Cannot found it......");
	    }
		
	    
	}
}
