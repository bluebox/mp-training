package challenge_10th_july;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsPractice {

	
	public static void main(String args[]) {

		List<String>sample=Arrays.asList("adheesh","tharun","gopi","ramu");
		List<String>ans=sample.stream().map(s->Character.toUpperCase(s.charAt(0))+s.substring(1)).collect(Collectors.toList());
		Map<Integer,Long>map=sample.stream().collect(Collectors.groupingBy(String::length,Collectors.counting()));
		Map<Integer,Long>map2
		System.out.println(ans);
		System.out.println(map);
	}
}
