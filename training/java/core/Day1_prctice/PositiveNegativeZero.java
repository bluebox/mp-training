package Day1_prctice;


public class PositiveNegativeZero {
//determine whether the number is zero,positive or negative
	public static void main(String args[]) {
		int num=5;
		String out=num==0?"Number is zero":(num>0?"Number is Positive":"Number is Negative");
		
		System.out.println(out);
	}
}
