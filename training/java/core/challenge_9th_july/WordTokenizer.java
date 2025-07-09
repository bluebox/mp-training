package challenge_9th_july;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordTokenizer {
	
	public static List<String> tokenizeFile(String filename){
		
		StringBuilder sb=new StringBuilder();
		try(BufferedReader reader=new BufferedReader(new FileReader("src/challenge_9th_july/sample.txt"))){
			String line;
			while((line=reader.readLine())!=null) {
				sb.append(line).append(" ");
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			return new ArrayList<>();
		}
		
		String finalString=sb.toString();
		
        String cleanedText = finalString.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();


        String[] words = cleanedText.split("\\s+");
		
//		String words[]=finalString.split(" ");
		List<String>s=Arrays.stream(words).filter(x->x.length()>5).collect(Collectors.toList());
		return s;
		
	}
	public static void main(String args[]) {
		String fileName="sample.txt";
		List<String> tokenizedFile=tokenizeFile(fileName);
		
		for(String s:tokenizedFile) {
			System.out.println(s);
		}
		Map<String,Integer>hm=new HashMap<>();
		for(String x:tokenizedFile) {
			hm.put(x,hm.getOrDefault(x,0)+1);
		}
		
//		  // Sort by value in descending order
//        LinkedHashMap<String, Integer> sortedMapDesc = hm.entrySet()
//                .stream()
//                .sorted()
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue,
//                        LinkedHashMap::new
//                ));
		
		 LinkedHashMap<String, Integer> top10 = hm.entrySet()
			        .stream()
			        .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
			        .limit(10)
			        .collect(Collectors.toMap(
			            Map.Entry::getKey,
			            Map.Entry::getValue,
			            (e1, e2) -> e1,
			            LinkedHashMap::new
			        ));
		 
		 System.out.println(top10);
		
	}
}
