import java.util.*;
public class ArrayListChallenge {
	
	static ArrayList<String> list=new ArrayList<>();
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		while(true)
			
		{
			System.out.println("Enter your choice:  \n 0:to shut down  \n 1:to add items \n 2:to remove items\n 3:display items ");
			int choice=s.nextInt();
			s.nextLine();
		
			switch(choice)
			{
			
			case 0:
				
				System.out.println("shutdown...");
				return ;
			case 1:
				addItems(s);
				break;
			case 2:
				removeItems(s);
				break;
			case 3:
				displayItems();
				break;
			default:
				System.out.println("Entered wrong choice..");
				
				
			}
		}
	}
	
	private static void addItems(Scanner s)
	{
		
		System.out.println("Enter Item to add");
		String item=s.nextLine();
		if(list.contains(item))
			System.out.println("The added item is already exists!");
		else
		{
		list.add(item);
		System.out.println("Item added succesfully");
		}
	}
	
	private static  void removeItems(Scanner s)
	{
		System.out.println("Enter item to remove");
		String item=s.nextLine();
		if(list.contains(item))
		{
			list.remove(item);
			System.out.println("Item was removed "+item);
		}
			
		else
			System.out.println("Item was not found");
	}
	private static void displayItems()
	{
		System.out.println("Items in the list are:");
		for(int i=0;i<list.size();i++)
		{
			
			System.out.println(" "+list.get(i));
			
		}
	}

}
