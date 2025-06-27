package Day1_prctice;

public class LargestofThree {
	public static void main(String args[]) {
		int a=20,b=30,c=40;
		int largest=a>b?(a>c?a:c):(b>c?b:c);
		
		System.out.println("The largest of three numbers is : "+ largest);	
	}
}
