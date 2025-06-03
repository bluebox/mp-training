package Generics;

import java.util.*;

public class GenericMethodToFindMax {
    public static <T extends Comparable<T>> T findMax(List<T>ans){
    	if(ans.size()==0) {
    		System.out.println("expected element to find max");
    		return null;
    	}
    	T maxi=ans.get(0);
    	for(T ele:ans) {
    		if(ele.compareTo(maxi)>0) {
    			maxi=ele;
    		}
    	}
    	return maxi;
    }
	public static void main(String[] args) {
		List<Integer>ans=new ArrayList<>(Arrays.asList(1,2,3,4,5));
		List<String>val=new ArrayList<>(Arrays.asList("happy","sad"));
		System.out.println(findMax(ans));
		System.out.println(findMax(val));
	}

}
