package june25_DataTypes;
import java.util.Scanner;
public class PoundsToKilograms {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of Pounds :");
		double pounds=sc.nextDouble();
	
		double kg=(pounds*0.45359237);
		System.out.println("Pounds: "+pounds+" is converted to Kilograms: "+kg+" Kg" );
		sc.close();
}
}