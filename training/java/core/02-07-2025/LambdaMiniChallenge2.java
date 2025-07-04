import java.util.*;
interface Lambda2
{
	String everySecondChar(String s);
}
public class LambdaMiniChallenge2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Lambda2 l=(s)->{
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
		System.out.println(l.everySecondChar("1234567890"));
		

	}

}
