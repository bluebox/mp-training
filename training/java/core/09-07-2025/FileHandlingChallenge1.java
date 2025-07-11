import java.io.File;
import java.util.*;
public class FileHandlingChallenge1 {
	public static void main(String args[])
	{
		
		Map<String,Integer> mp=new HashMap<>();
		try {
			File f=new File("./sample.txt");
			Scanner s=new Scanner(f);
			while(s.hasNext())
			{
				String word=s.next();
				word.replaceAll("[^a-zA-Z][//.//,]","").toLowerCase();
				if(word.length()>5)
				{
					mp.put(word, mp.getOrDefault(word,0)+1);
				}
				
			}
			List<Map.Entry<String, Integer>> list=new ArrayList<>(mp.entrySet());
			list.sort((a,b)->b.getValue().compareTo(a.getValue()));
			
			for(int i=0;i<Math.min(list.size(), 10);i++)
			{
				Map.Entry<String, Integer> entry=list.get(i);
				System.out.println(entry.getKey()+"  "+entry.getValue());
			}
				
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}

}
