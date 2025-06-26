
public class SumFirstLast {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Sum of first and last digit of the 25 : "+sum(25));
		System.out.println("Sum of first and last digit of the 252 : "+sum(252));
		System.out.println("Sum of first and last digit of the 32566 : "+sum(32566));
		System.out.println("Sum of first and last digit of the -25 : "+sum(-25));
		System.out.println("Sum of first and last digit of the 5 : "+sum(5));

	}
	
	public static int sum(int num) {
		
		int temp=num;
		if(temp<0) {
			num*=-1;
		}
		
		int l=num%10;
		
		num=num/10;
		
		int f=0;
		while(num>0) {
			f=num%10;
			num=num/10;
		}
		if(temp<0) {
			f*=-1;
		}
		return f+l;
	}

}
