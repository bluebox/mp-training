import java.util.*;

public class ArrayListGrocery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		while(true) {
			System.out.print("Available  actions: \n 0 - to shutdown \n 1 - to add item(s) to list \n 2 - to remove for any items \n Enter a number for which action you want to do: ");
			int menu=sc.nextInt();
			if(menu ==0) break;
			if(menu ==1){
				System.out.print("Enter the Item(s) (comma separated) to add to the list:");
				String item=sc.next();
				String[] itemsToBeAdded=item.split(",");
				for(String i: itemsToBeAdded) {
					if(!list.contains(i)) {
						list.add(i);
					}
				}
				//list.addAll(Arrays.asList(item.split(",")));
			}
			else if(menu==2){
				System.out.print("Enter the Item(s) (comma separated) to be deleted:");
				String item=sc.next();
				String[] itemsToBeRemoved=item.split(",");
				for(String i: itemsToBeRemoved) {
					if(list.contains(i)) {
						list.remove(i);
					}
				}
			}
			Collections.sort(list);
			System.out.println(String.join(" ", list));
		}

	}

}
