package Streams;

import java.util.*;
import java.util.stream.Collectors;

public class SortingUsingStreams {

	public static void main(String[] args) {
         List<Integer>ans=new ArrayList<>(Arrays.asList(5,4,3,2,1));
         List<Integer>sortedans=ans.stream()
        		 .map(i->i)
        		 .sorted()
        		 .collect(Collectors.toList());
         for(Integer ele:sortedans) {
        	 System.out.println(ele);
         }
         
         sortedans.forEach(i->System.out.print(i+" "));
	}

}
