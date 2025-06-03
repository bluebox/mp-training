package Maps;

import java.util.*;

public class TwoSumUsingHashMap {
    public static boolean solve(List<Integer>ans,int tar) {
    	Map<Integer,Integer>mp=new HashMap<>();
    	for(Integer ele:ans) {
    		int rem=tar-ele;
    		if(mp.getOrDefault(rem,0)>0)return true;
    		mp.put(ele, mp.getOrDefault(ele, 0)+1);
    	}
    	return false;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		List<Integer>ans=new ArrayList<>();
		for(int i=0;i<n;i++) {
			ans.add(sc.nextInt());
		}
		int tar=sc.nextInt();
		if(solve(ans,tar)) {
			System.out.println(tar+" is found in the array ans");
		}
		else {
			System.out.println(tar +" not found");
		}
	}

}
