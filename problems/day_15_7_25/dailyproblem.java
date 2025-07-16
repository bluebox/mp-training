package day_15_7_25;
import java.util.*;
public class dailyproblem {

	public static void main(String[] args) {
//		Scanner sc=new Scanner(System.in);
//		int length=sc.nextInt();
//		Random random=new Random();
//	int []arr= random.ints(100) 
//                .limit(length) 
//                .map(Math::abs)
//                .toArray(); 
		
		
		int arr[]= {3,30,34,5,9};
		
		
		
//		int arr[]= {0,0,0,99,9};
	    ArrayList<String> sb=new ArrayList<>();
	    PriorityQueue<String> pq=new PriorityQueue<>((a,b)->b.compareTo(a));
	 Set<Integer> set=new HashSet<>();
		   findcombinations(0,arr,sb,pq,set);	  
	System.out.println(pq.peek());
		

	}
	
	
	
	
	public static void findcombinations(int ind,int [] arr,ArrayList<String> sb,PriorityQueue<String> pq,Set<Integer> set) {
		if(sb.size()==arr.length) {
			String ret=sb.stream()
					.reduce("", (a,b)->a+b);
			pq.add(ret);
			return;
		}
		
		for(int i=0;i<arr.length;i++) {
			if(!set.contains(i)) {
			set.add(i);
			sb.add(""+arr[i]);
			findcombinations(i+1,arr,sb,pq,set);
		    sb.remove(sb.size()-1);
		    set.remove(i);
			}
	}
	}	
}
