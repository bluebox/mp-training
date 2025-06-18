import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TripletCount {
      public static void main(String []args) {
    	  System.out.println("enter the size");
    	  Scanner sc=new Scanner(System.in);
          int n=sc.nextInt();
          List<Integer>ans=new ArrayList<>();
          for(int i=0;i<n;i++) {
        	  int val=sc.nextInt();
        	  ans.add(val);
          }
          System.out.println(triplets(ans));
      }

	  private static int triplets(List<Integer> ans) {
		 int cnt=0;
		 int mod=1000_000_000;
		 Map<Integer,Integer>mp=new HashMap<>();
		 Map<Integer,Integer>up=new HashMap<>();
		 for(Integer ele:ans) {
			 mp.put(ele,mp.getOrDefault(ele,0)+1);
		 }
		 for(int i=0;i<ans.size();i++) {
			 int val=ans.get(i);
			 mp.put(val,mp.get(val)-1);
			 cnt=(int)(cnt+1L*mp.getOrDefault(val*2, 0)*up.getOrDefault(val*2,0))%mod;
			 up.put(val,up.getOrDefault(val, 0)+1);
		 }
		 return cnt;
	  }
}
