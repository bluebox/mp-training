package challanges;
import java.util.*;
public class remainConsumers {
	
	public static int getRemainConsumers(int[] persons,int[] dishes) {
		Queue<Integer> perque=new LinkedList<>();
		Stack<Integer> dishque=new Stack<>();
		int n=dishes.length-1;
		for(int per:persons) {
			perque.add(per);
			dishque.push(dishes[n--]);
		}
	
		System.out.println("The persons queue is : "+perque);
		System.out.println("The dishes queue is : "+dishque);
		int remConsumers=0;
		
		while(!perque.isEmpty() && !dishque.isEmpty()) {
			if(perque.peek().equals(dishque.peek())) {
				perque.poll();
				dishque.pop();
				remConsumers=0;
			}
			else {
				perque.add(perque.poll());
				remConsumers++;
			}
			
			if(remConsumers==perque.size()) {
				break;
			}
		}
		
		return remConsumers;
	}

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the no of persons : ");
		int np=sc.nextInt();
		int[] persons=new int[np];
//		int[] persons= {1,1,0,1,0,1,1};
		for(int i=0;i<np;i++) {
			System.out.print("Enter the "+(i+1)+" person as 1 or 0 type : ");
			persons[i]=sc.nextInt();
		}
		int[] dishes=new int[np];
//		int[] dishes= {0,1,0,1,1,0,0};
		for(int i=0;i<np;i++) {
			System.out.print("Enter the dish of "+(i+1)+" as 1 or 0 type : ");
			dishes[i]=sc.nextInt();
		}
		System.out.println("Person s are : "+Arrays.toString(persons));
		System.out.println("Dishes s are : "+Arrays.toString(dishes));
		int ans=getRemainConsumers(persons,dishes);
		System.out.println("The remaining persons are : "+ans);
		sc.close();
	}

}
