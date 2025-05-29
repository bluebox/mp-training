package LinkedList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class InteractiveConsole {
	
	
	
	public static void main(String[] args) {
		LinkedList<City> list = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		
do {
			
			System.out.println("Available actions");
			System.out.println("0 - to Shutdown");
			System.out.println("1 - to add item to list");
			System.out.println("2 - to remove any items ");
			System.out.println("3 - to traverse the list");
			System.out.println("Enter a number for which action you want to do:: ");
			
			String option =sc.nextLine();
			
			if(option.equals("1"))
			{
				
				System.out.println("Enter name of the city :: ");
				String name = sc.nextLine();
				System.out.println("Enter the distance");
				double distance = sc.nextDouble();
				sc.nextLine();
				
				City c = new City(name,distance);
				
				list.add(c);
				
				System.out.println("City added to list. "+c);
				
			}else if(option.equals("2"))
			{
				
				System.out.println("Enter the City name for deletion:: ");
				String name = sc.nextLine();
				for(int i = 0;i<list.size();i++)
				{
					if(list.get(i).getCity().equals(name))
					{
						list.remove(i);
					}
				}
			}else if(option.equals("3"))
			{
				ListIterator<City> iterator = list.listIterator();
				do {
					boolean flag = false;
					System.out.println("Available actions (select word or letters)");
					System.out.println("(F)orward");
					System.out.println("(B)ackward");
					System.out.println("(L)ist places");
					System.out.println("(M)enu");
					
					String innerOption =sc.nextLine();
					
					switch(innerOption.toLowerCase()) {
						case "forward","f" ->{
							if(iterator.hasNext())
							{
								System.out.println("next element :: "+iterator.next());
							}else
							{
								System.out.println("Reached the end of the linked list");
							}
						}
						case "backward","b"->{
							if(iterator.hasPrevious())
							{
								System.out.println("Previous element :: "+iterator.previous());
							}else
							{
								System.out.println("Reached the begining of the linked list");
							}
						}
						case "list place","l"->{
							for(City c1:list)
							{
								System.out.println(c1);
							}
							System.out.println("Current index :: "+(iterator.nextIndex()));
						}
						default ->{flag = true;
						break;
						}
					}
						if(flag)
						{
							break;
						}
					}while(true);
			}
			else {
				break;
			}
			
		}while(true);
		
		
		
		
	}
}
