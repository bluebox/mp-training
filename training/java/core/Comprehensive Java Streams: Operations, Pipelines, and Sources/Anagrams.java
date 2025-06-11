import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
public class Anagrams {

	public static void main(String[] args) {
		ArrayList<String> lis=new ArrayList<String>(List.of("care","race","acer","acre","spy","psy","game","mage","mega","Bike"));
		Set<String> set=new TreeSet<>();
		Map<String,ArrayList<String>> anag=new HashMap< String,ArrayList<String>>();
		for(var member:lis) {
			char[] item=member.toCharArray();
			Arrays.sort(item);
			String s=new String(item);
			if(anag.containsKey(s)){
			    anag.get(s).add(member);
			}
			else{
			    anag.put(s,new ArrayList<String>(List.of(member)));
			}
		}
		ArrayList<ArrayList<String>>  anagrams=new ArrayList<ArrayList<String>>();
		for(var key:anag.entrySet()){
		    anagrams.add(key.getValue());
		}
		System.out.println(anag+" "+anagrams);
		
		
		
	}
}