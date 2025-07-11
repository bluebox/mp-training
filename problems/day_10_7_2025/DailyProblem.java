package day_10_7_2025;
import java.util.*;
public class DailyProblem {
	static ArrayList<Integer> temp=new ArrayList<>();
	
	public static void main(String[] args) {
		int [] arr= {1,4,5};
		//ArrayList<ArrayList<Integer>> powerset=new ArrayList<>();
		
       // powersets(powerset,arr,0);
        powerset(arr);
        
       // powerset.forEach(System.out::println);
	}
	
//	public static void powersets(ArrayList<ArrayList<Integer>> result,int []arr,int i) {
//		if(i==arr.length) {
//			result.add(new ArrayList<>(temp));
//			return;
//		}
//		
//		temp.add(arr[i]);
//		powersets(result,arr,i+1);
//		temp.remove(temp.size()-1);  	
//		powersets(result,arr,i+1);
//		
//	}
	
	public static void powerset(int []arr) {
		   int numof=(int)Math.pow(2,arr.length) ;
		   List<ArrayList<Integer>> list=new ArrayList<>();
		   for(int i=0;i<numof;i++) {
			   ArrayList<Integer> temp=new ArrayList<>();
			   int arr1[]=new int[arr.length];
			   int num=arr.length;
			   for(int j=0;j<num;j++) {
			   if((i & (1 << j)) != 0) {
                   temp.add(arr[j]);
               }
			   }
			   list.add(temp);
		   }
		   
		   list.forEach(System.out::println);
		   
		   
	}
	

}
