import java.util.Scanner;

public class SharedDigit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		System.out.println("Enter Two Numbers :");
		int a=sc.nextInt(),b=sc.nextInt();
		
		common(a,b);
		
	}
	
	public static void common(int a,int b) {
		
		if(a>=10 && a<=99 && b>=10 && b<=99) {
			int x=a%10;
			a=a/10;
			int y=a%10;
			int z=b%10;
			b=b/10;
			int w=b%10;
			
			if(x==z || x==w) {
				System.out.println("Common digit :"+x);
			}else if(y==z || y==w) {
				System.out.println("Common digit :"+y);
			}
			
		}
		else{
			System.out.println("Invalid");
		}
	}
	

}
