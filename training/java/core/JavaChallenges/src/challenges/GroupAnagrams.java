package challenges;

import java.util.*;
public class GroupAnagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> l=new ArrayList<>();
		l.add("abc");
		l.add("cba");
		l.add("b");
		l.add("bb");
		l.add("uday");
		l.add("dayu");
		HashMap<String,List<String>> hm=new HashMap<>();
		for(String s:l)
		{
			char ch[]=s.toCharArray();
			Arrays.sort(ch);
			hm.putIfAbsent(String.valueOf(ch),new ArrayList<>());
			hm.get(String.valueOf(ch)).add(s);
		}
		List<List<String>> res=new ArrayList<>();
		for(List<String> v:hm.values())
		{
			res.add(v);
		}
		System.out.println(res);

	}
	
	

}
