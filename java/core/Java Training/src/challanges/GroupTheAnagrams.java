package challanges;
import java.util.*;

public class GroupTheAnagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] s= {"ba","abc","xyz","nm","yxz","cba","ab"};
		HashMap<String,ArrayList<String>> hm=new HashMap<>();
		for(int i=0;i<s.length;i++) {
			char[] x=s[i].toCharArray();
			Arrays.sort(x);
			String str=new String(x);
			System.out.println(s[i]+"->"+str);
			if(hm.containsKey(str)) {
				hm.get(str).add(s[i]);
			}
			else {
				hm.put(str, new ArrayList<>(List.of(s[i])));
			}
		}
		System.out.println("Group of Anagrams list :");
		hm.forEach((key,val)->System.out.println("	"+key+"	->	"+val));
	}

}
