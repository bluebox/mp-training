package Streams;

import java.util.*;
import java.util.stream.Collectors;

public class FilteringUsingStream {

	public static void main(String[] args) {
		List<String>ans=new ArrayList<>(Arrays.asList("Shiva","Ram","shiva","ram","Anand","anand"));
		List<String>filteredans=ans.stream()
				.filter((i)->isCapital(i))
				.map(i->i)
				.collect(Collectors.toList());
		filteredans.forEach(System.out::println);
	}

	private static boolean isCapital(String i) {
		return i.charAt(0)>='A' && i.charAt(0)<='Z';
	}

}
