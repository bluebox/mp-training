package ArrayListChallenge;
import java.util.*;
public class ArrayListGroc {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList<String> ans=new ArrayList<>();
		while(true) {
			int n;
			System.out.println("0 to shutdown");
			System.out.println("1 to add item");
			System.out.println("2 to remove item");
			n=sc.nextInt();
			sc.nextLine();
			if(n==0) {
				break;
			}
			else if(n==1) {
				System.out.println("enter item");
				String item=sc.nextLine();
				ans.add(item);
			}
			else{
				String item=sc.nextLine();
				if(ans.contains(item)) {
					ans.remove(item);
					System.out.println("removed successfully");
				}
				else {
					System.out.println("item not found");
				}
			}
		}
		System.out.println("remaining ele in sorted order");
		Collections.sort(ans);
		for(String ele:ans)System.out.println(ele);
	}

}
