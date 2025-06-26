import java.util.Scanner;

public class EvenNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc =new Scanner(System.in);
		System.out.print("Enter a number: ");
		int num= sc.nextInt();
		
		int even=0,odd=0;
		
		for(int i=0;i<num;i++) {
			if(i%2 == 0) {
				even++;
			}
			else {
				odd++;
			}
		}
		
		System.out.println("Total Even and Odd nums in the given range is :"+even+" "+odd+" "+"respecticely");
		
		
		int a=5,b=20;
		
		while(a<=b) {
			if(isEven(a)) {
				System.out.println(a);
			}
			a++;
		}
	}
	
	public static boolean isEven(int num) {
		if(num%2==0) {
			return true;
		}
		return false;
	}

}
