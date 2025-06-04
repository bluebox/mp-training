package challenges;

import java.util.function.Function;

public class MiniChallenge2 {
	
	public static void main(String[] args) {
		Function<String, String> getSecond = (s1)->{
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i<s1.length();i = i+2)
			{
				sb.append(s1.charAt(i));
			}
			return sb.toString();
		};
		
		System.out.println(getSecond.apply("Helloworld"));
	}
	

}
