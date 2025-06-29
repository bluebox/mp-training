package june26_methods;
import java.util.Scanner;
public class InputthenSumAndAvg {
	public static void inputThenSumAndAvg() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter numbers");
		int sum=0;
		int count=0;
		int x=1;
		while(x==1) 
		{
			if(sc.hasNextInt()) {
				int num=sc.nextInt();
				sum+=num;
				count+=1;}
			else {
				x=0;
				break;
			}
			
				
		}
		if(count==0)
			System.out.println("Sum=0\navg=0");
		else	
		System.out.println("Sum :"+sum+"\nAvg : "+(sum/count));
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inputThenSumAndAvg();
		
	
	}

}
