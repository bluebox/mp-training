import java.util.Scanner;
public class EvenDigitSum {
	
	public static int getEvenDigitSum(int n) {
		int sum=0;
		if(n<0) {
			return -1;
		}
		String s=Integer.toString(n);
		System.out.println(s);
		for(int i=1;i<=s.length();i++) {
			if(i%2==0) {
				String ch=s.charAt(i);
				int d=Integer.parseInt(ch);
				sum+=d;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter the number : ");
		int n=scanner.nextInt();
		int ans=getEvenDigitSum(n);
		if(ans>0) {
			System.out.println("The even digits sum of "+n+" is : "+ans);
		}
		else {
			System.out.println("Inavalid input");
		}
		scanner.close();
	}

}
