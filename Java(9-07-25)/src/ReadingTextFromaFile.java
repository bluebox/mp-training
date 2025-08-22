import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadingTextFromaFile {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f1=new File("./testing.txt");
		if(!f1.exists())
		{
			System.out.println(f1.createNewFile());
		}
		Scanner sc=new Scanner(f1);
		ArrayList<String>al=new ArrayList<>();
		while(sc.hasNextLine())
		{
			al.add(sc.next());
		}
		HashMap<String,Integer>hm=new HashMap<>();
		for(String s:al)
		{
			System.out.println(s);
			if(hm.containsKey(s))
			{
				hm.put(s,hm.get(s)+1);
			}
			else
			{
				hm.put(s, 1);
			}
		}
		
		sc.close();
		
	}

}
