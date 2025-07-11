package day_9_7_2025;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FileReading {

	public static void main(String[] args) throws FileNotFoundException , IOException{
		String file="C://Users//DELL//eclipse-workspace//MyJava//src//day_9_7_2025//story.txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			Map<String,Integer> map=new TreeMap<String,Integer>();
			
			String line;
			while((line=reader.readLine()) != null) {
				String arr[]=line.replaceAll("\\p{P}","").split(" ");
				System.out.println("hi hello"+arr[0]);
				for(String str:arr) {
				map.put(str,map.getOrDefault(str,0)+1);
				}
				
			    for(String str:map.keySet()) {
			    	System.out.println(str+" "+map.get(str));
			    }
			    List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
				Collections.sort(list,(a,b)->b.getValue().compareTo(a.getValue()));   	
				list.stream()
				           .filter((k)->k.getKey().length()>=5)
				           .limit(10)
				           .forEach(s->System.out.println(s.getKey()+" "+"I am the most repeated"));
				
			}
		}catch(RuntimeException e) {
			System.out.println("File not found sorry");
			System.out.println(e);
		}
		
		
	}

}
