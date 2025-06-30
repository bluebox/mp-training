import java.util.Scanner;

public class Sum_And_Average {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int sum=0,count=0;
		while(true) {
			System.out.println("Enter the numbers:");
			try {
				int num= sc.nextInt();
				sum+=num;
				count++;
			}
			catch(Exception e) {
				System.out.println();
				break;
			}
		}
		long average =0;
		if(count>0) {
			average= ((long)sum)/count;
		}
		System.out.println("SUM = "+sum+" AVG = "+average);

	}

}
