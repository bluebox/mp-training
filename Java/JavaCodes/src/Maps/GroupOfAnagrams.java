package Maps;

import java.util.*;

public class GroupOfAnagrams {
    public static void solve(Map<String,List<String>>mp,List<String>anagrams) {
    	for(String ele:anagrams) {
    		char [] ch=ele.toCharArray();
    		Arrays.sort(ch);
    		String t=new String(ch);
    		mp.putIfAbsent(t,new ArrayList<>());
    		mp.get(t).add(ele);
    	}
    }
	public static void main(String[] args) {
		List<String> anagrams = Arrays.asList(
			    "listen",
			    "silent",
			    "enlist",
			    "inlets",
			    "tinsel",
			    "evil",
			    "vile",
			    "live",
			    "veil"
			);
		Map<String,List<String>>mp=new HashMap<>();
		solve(mp,anagrams);
		for(Map.Entry<String,List<String>> entry:mp.entrySet()) {
//			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			System.out.println();
		}
	}

}
