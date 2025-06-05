package samplecodes;
import java.io.*;
import java.util.*;
public class FileReaderChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br=new BufferedReader(new FileReader("samplecodes/textfile.txt"));
			String line;
			int max=1;
			Map<String,Integer> tokens=new HashMap<>();
			while((line=br.readLine())!=null) {
				StringTokenizer sb=new StringTokenizer(line);
				while(sb.hasMoreTokens()) {
					String s=sb.nextToken();
					if(s.length()>=5) {
						if(tokens.containsKey(s)) {
							tokens.put(s, tokens.get(s)+1);
							max=Math.max(max, tokens.get(s));
						}else tokens.put(s,1);
					}
				}
			}
			System.out.println(tokens);
			System.out.println();
			Set<Integer> freq = new HashSet<>();
			for(String s:tokens.keySet()) {
				freq.add(tokens.get(s));
			}
			ArrayList<Integer> l=new ArrayList<>(freq);
			Collections.sort(l);
			int cnt=0;
			for(int i=l.size()-1;i>=0;i--) {
				for(String s:tokens.keySet()) {
					if(cnt==10) break;
					if(tokens.get(s)==l.get(i)) {
						System.out.println(s +"   "  +l.get(i));
						cnt++;
					}
				}
			}
			
			
			
			System.out.println();
			
			
			 tokens.entrySet().stream()
	          .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	          .limit(10)
	          .forEach(entry -> System.out.println(entry.getKey()+" "+entry.getValue()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
