package samplecodes;

public class LastDigitEvaluation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(hasSameLastDigit(41,22,71));
	}
	public static boolean hasSameLastDigit(int a,int b,int c) {
		if(!isValid(a) || !isValid(b) || !isValid(c)) return false;
		String s1=Integer.toString(a);
		String s2=Integer.toString(b);
		String s3=Integer.toString(c);
		if(s1.charAt(s1.length()-1)==s2.charAt(s2.length()-1)) return true;
		if(s2.charAt(s2.length()-1)==s3.charAt(s3.length()-1)) return true;
		if(s1.charAt(s1.length()-1)==s3.charAt(s3.length()-1)) return true;
		return false;
		
		
	}
	public static boolean isValid(int n) {
		return n>=10 && n<=1000;
	}

}
