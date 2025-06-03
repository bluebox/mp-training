import java.util.stream.IntStream;

public class LambdaChallenge {

	public static String everySecond(String s) {
		StringBuilder t=new StringBuilder();
	    IntStream.range(0,s.length())
	       .forEach((i)->{
	    	  if(i%2==0)t.append(s.charAt(i)); 
	       });
	    return t.toString();
	}
	public static void main(String[] args) {
		 String s="hello world";
		 System.out.println(everySecond(s));
	}

}
