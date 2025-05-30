package Sets;

import java.util.*;

public class FindingDuplicatesUsingSet {

	public static void main(String[] args) {
		// finding all Duplicate elements
		List<Integer> ans=new ArrayList<>();
		System.out.println("enter the size of the array");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for(int i=0;i<n;i++) {
			ans.add(sc.nextInt());
		}
		Set<Integer>st=new HashSet<>(),ut=new HashSet<>();
		System.out.println("Duplicates");
		for(Integer ele:ans) {
			if(!st.add(ele)) {
				ut.add(ele);
			}
		}
		for(Integer ele:ut) {
			System.out.print(ele+" ");
		}
	}

}
