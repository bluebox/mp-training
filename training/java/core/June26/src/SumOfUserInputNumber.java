import java.util.Scanner;
public class SumOfUserInputNumber {
	public static void main(String[] args) {
		
		
		int count =1 ;
		int sum =0;
		
		try (Scanner sc = new Scanner(System.in)) {
			while(count <= 5) {
				System.out.print("Enter number #"+count+": ");
				String number=sc.nextLine();
				try {
					
					sum+=Integer.parseInt(number);
					count++;
					
				}catch(NumberFormatException e) {
					
					System.out.println("Invalid number");
					
				}
			}
			System.out.println("Sum is: "+sum);
		}catch(Exception e) {
			System.out.print("Error while Creating Scanner Object");
		}
	}
}
