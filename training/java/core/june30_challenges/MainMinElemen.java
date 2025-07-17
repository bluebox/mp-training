package june30_collections;
import java.util.Scanner;
public class MainMinElemen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter numbers separated by commas:");
		String input=sc.nextLine();
		
		int[] arr=MinElement.readIntegers(input);
		
		System.out.println("Minimum element in array :"+MinElement.Min_Element(arr));
		sc.close();
	}

}
