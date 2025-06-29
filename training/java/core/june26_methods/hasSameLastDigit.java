package june26_methods;
import java.util.Scanner;
public class hasSameLastDigit {
	public static boolean hasSameLastDigits(int a,int b,int c) {
		if((a<10 || a>1000)||(b<10 || b>1000)||(c<10||c>1000))
			return false;
		int la=a%10;
		int lb=b%10;
		int lc=c%10;
		if((la==lb)||(lb==lc)||(lc==la)) {
			return true;
		}
		else
			return false;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 3 numbers within the range 10-1000(both INCLUSIVE)");
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		boolean result=hasSameLastDigits(a,b,c);
		if(result==false)
			System.out.println("numbers are not in range (OR) numbers last digit is not matching!!!");
		else
			System.out.println("Numbers last digit is same for atleast 2 numbers");
		sc.close();
	}

}
