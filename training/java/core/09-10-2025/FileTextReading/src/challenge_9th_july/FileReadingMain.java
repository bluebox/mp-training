package challenge_9th_july;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReadingMain {
	public static void main(String[] args) {
        String filePath = "src/challenge_9th_july/one.txt";
        Map<String,Integer>count=new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
//            System.out.println("Reading file: ");
            while ((line = br.readLine()) != null) 
            {
//                System.out.println(line);
                line=line.replaceAll("[^a-zA-z]"," ").toLowerCase();
//                System.out.println("After removing all except letters: ");
                System.out.println(line);
                String [] words=line.split("\\s+"); 
                for(String s:words)
                {
                	if(s.length()>5)
                	{
                		count.put(s, count.getOrDefault(s,0)+1);
                	}
                }
               // System.out.println(count);
            
            }
            
        }
        catch (IOException e) 
        {
            System.out.print(e.getMessage());;
        }
        ArrayList<Map.Entry<String,Integer>>afterSort=new ArrayList<>(count.entrySet());
        afterSort.sort((e1,e2)->e2.getValue().compareTo(e1.getValue()));
        System.out.println("Top most 10 words : ");
       
        for(int i=0;i<10;i++)
        {
        	 Map.Entry<String,Integer>etry1=afterSort.get(i);
             System.out.println(etry1.getKey()+"  :"+etry1.getValue());
        }
        
        
        
    }
}

