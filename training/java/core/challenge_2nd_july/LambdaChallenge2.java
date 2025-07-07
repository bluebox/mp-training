package challenge_2nd_july;

import java.util.*;
interface Lambda
{
	String everySecondChar(String s);
}
public class LambdaChallenge2 {

	public static void main(String[] args) {
		
		Lambda l=(s)->{
			StringBuilder returnVal=new StringBuilder();
			for(int i=0;i<s.length();i++)
			{
				if(i%2==1)
				{
					returnVal.append(s.charAt(i));
				}
			}
			return returnVal.toString();
		};
		System.out.println(l.everySecondChar("1234567"));
	
	}

}