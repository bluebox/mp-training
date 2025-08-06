import java.util.Scanner;

public class MinAndMax {
	public static void main(String[] args) {
		double min=1;
		double max =1;
		try (Scanner sc = new Scanner(System.in)) {
			while(true) {
				System.out.print("Enter number or any character for quit ");
				String string=sc.nextLine();
				try {
					
					double number=Double.parseDouble(string);
					if(min > number) {
						min = number;
					}
					if(max < number) {
						max = number;
					}
					
				}catch(NumberFormatException e) {
					
					System.out.println("Invalid number");
					break;
				}
			}
			System.out.println("Min is: "+min);
			System.out.println("Max is: "+max);
		}catch(Exception e) {
			System.out.print("Error while Creating Scanner Object");
		}
	}
}
