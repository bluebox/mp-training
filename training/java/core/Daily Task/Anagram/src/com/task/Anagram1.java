package com.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Anagram1 {
	public static void main(String[] args) {
	 List<String> a=(List.of("bin","Bin","nib","eat","tea","bear","bare","fare","fear","pain"));
		Map<String, List<String>> l=a.stream()
				.collect(Collectors.groupingBy(s->Sorting.sorting(s)));
		for(Object i:l.keySet()) {
			System.out.println(l.get(i));
		}
		System.out.println(l);
		System.out.println("\nAnagrams are : ");
		for(Object i:l.keySet()) {
			List<String> x = l.get(i);
			if(x.size()>1) {
				System.out.println(x);
			}
		}
	}
}
