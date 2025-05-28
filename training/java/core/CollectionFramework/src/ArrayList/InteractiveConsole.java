package ArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InteractiveConsole {
	
	public static void main(String[] args) {
		ArrayList<Integer>arr = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		do {
			
			System.out.println("Available actions");
			System.out.println("0 - to Shutdown");
			System.out.println("1 - to add item(s) to list (comma delimited list)");
			System.out.println("2 - to remove any items (comma delimited list");
			System.out.println("Enter a number for which action you want to do:: ");
			
			String option =sc.nextLine();
			
			if(option.equals("1"))
			{
				System.out.println("Enter the comma delimited list");
				String array = sc.nextLine();
				String[] nums = array.split(",");
				int size = nums.length;
				Integer[] intArray = new Integer[size];
				for(int i = 0; i<size;i++)
				{
					intArray[i] = Integer.parseInt(nums[i]);
				}
				arr.addAll(Arrays.asList(intArray));
				System.out.println("After additon :: "+arr);
			}else if(option.equals("2"))
			{
				System.out.println("Enter the comma delimited items for delition");
				String array = sc.nextLine();
				String[] nums = array.split(",");
				int size = nums.length;
				Integer[] intArray = new Integer[size];
				for(int i = 0; i<size;i++)
				{
					intArray[i] = Integer.parseInt(nums[i]);
				}
				
				for(int i = 0;i<size;i++)
				{
					boolean isPresent = arr.remove(intArray[i]);
					if(!isPresent)
					{
						System.out.println(""+intArray[i]+" is not present");
					}
				}
				System.out.println("After deletion :: "+arr);
			}else {
				break;
			}
			
		}while(true);
		
	}
	
	
}
