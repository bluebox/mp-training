package Tasks;
import java.util.*;
public class Switch {
		public static void main (String[] args) {
			Scanner sc = new Scanner(System.in);
			int  n =sc.nextInt();
			String ans="";
			switch(n) {
			case 0: 
				ans="Sunday";
				break;
			case 1: 
				ans="Monday";
				break;
			case 2: 
				ans="Tuesday";
				break;
			case 3: 
				ans="Wednesday";
				break;
			case 4: 
				ans="THursday";
				break;
			case 5: 
				ans="Friday";
				break;
			case 6: 
				ans="Satday";
				break;
			
			}
			System.out.println(ans);
			
			
}
}
		
