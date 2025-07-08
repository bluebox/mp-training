import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> list;
		list=getRandomList();
		System.out.println(list);
		boolean run=true;
		while(run) {
			System.out.println("Press 0 for exit and score :");
			System.out.println("Press  1 for All :");
			System.out.println("press 2  to re roll the selected dice until u enter the -1 :");
			Scanner sc=new Scanner(System.in);
			int n=sc.nextInt();
			switch(n) {
				case 0: {
					System.out.println("Game Over .");
					run=false;
					break;
				}
				case 1:{
					list=getRandomList();
					System.out.println(list);
					break;
				}
				case 2:{
					System.out.println("Choose the index from 0 to 4  else choose -1:");
					while(true) {
						int dice=sc.nextInt();
						if(dice==-1)break;
						list.remove(dice);
						list.add(dice,getRandomValue());
					}
					System.out.println(list);
					break;
				}
				default:System.out.println("Enter the valid input:");
			}
		}
	}
	public static ArrayList<Integer> getRandomList() {
		Random rand=new Random();
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<5;i++) {
			list.add(rand.nextInt(6)+1);
		}
		return list;
	}
	public static int getRandomValue() {
		Random ran=new Random();
		return ran.nextInt(6)+1;
	}
}
