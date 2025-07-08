package day_3_7_2025;
import java.util.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class dailyProblem {

	public static void main(String[] args) {
		int arr[][]= {{0,5},{4,8},{8,9},{0,10},{10,23}};
////		List<List<Integer>> dfb=new ArrayList<>();
////		int arr1[]= {23,34};
////		dfb.add(n)));
////		dfb.add(Arrays.asList(1,3,3));
////		for(List<Integer> inerList : dfb) {
////			System.out.println(inerList.get(0));
////		}
	List<List<Integer>> set=new ArrayList<>();
		ArrayList<Boolean> retlist=new ArrayList<>();
////		System.out.println(arr[0].length);
	set.add(List.of(arr[0][0],arr[0][1]));
	retlist.add(true);
////		for(List<Integer> fb:set) {
////			System.out.println(fb.size());
////			for(int i=0;i<fb.size();i++) {
////				System.out.println(fb.get(i));
////			}
////		}
//		retlist.add(true);
//		for(int i=1;i<arr.length;i++) {
//			int [] temp=arr[i]; 
//			 for(List<Integer> v:set) {
//					if(v.get(0) <= temp[0]) {
//						set.add(new ArrayList(Arrays.asList(temp)));
//						retlist.add(true);
//						//break;
//					}else{
//						int min=Math.min((int)v.get(0),temp[0]);
//						int max=Math.max((int)v.get(1),temp[1]);
//						set.add(new ArrayList(List.of(min,max)));
//						retlist.add(false);
//						//break;
//					}
//					
//		        }
//			}
//		
//		
//		retlist.forEach(System.out::println);
		
//		retlist.add(true);
		for(int i=1;i<arr.length;i++) {
			int [] temp=arr[i];
			ListIterator<List<Integer>> it = set.listIterator();
			 while (it.hasNext()) {
				 List<Integer> v=it.next();
				    System.out.println(i);
					if((int)v.get(1) <= temp[0]) {
						it.add(List.of(arr[i][0],arr[i][1]));
						retlist.add(true);
						break;
					}else{
						int min=Math.min((int)v.get(0),temp[0]);
						int max=Math.max((int)v.get(1),temp[1]);
						it.set(List.of(min,max));
						retlist.add(false);
						break;
					}
					
		        }
			}
		
		
		retlist.forEach(System.out::println);
		
		
		
		
	}
	
	

}
