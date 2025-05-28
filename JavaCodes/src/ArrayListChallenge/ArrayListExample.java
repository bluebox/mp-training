package ArrayListChallenge;
import java.util.*;
public class ArrayListExample {

	public static void main(String[] args) {
		 ArrayList<Integer> ans = new ArrayList<>();
		 ans.add(10);
	     ans.add(20);
	     ans.add(30);
	     ans.add(0,1000);
         for(Integer ele:ans) {
        	 System.out.println(ele);
         }
         List<Integer> val=new ArrayList<>();
         val.add(100);
         for(Integer ele:val)System.out.println(ele);
	}

}
