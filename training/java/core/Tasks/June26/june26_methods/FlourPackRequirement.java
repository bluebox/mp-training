package corejava.june26_methods;

import java.util.Scanner;

public class FlourPackRequirement {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no of 5 kilo bags we have");
		byte bigBag=sc.nextByte();
		System.out.println("Enter no of 1 kilo bags we have");
		byte smallBag=sc.nextByte();
		System.out.println("Enter the amount of flour need to be packed in kg's");
		int goal=sc.nextInt();
		if(canPack(bigBag,smallBag,goal)) {
			System.out.println("We can pack "+goal+" kg's of flour in ("+bigBag+")bigBags and ("+smallBag+")smallBags");
		}
		else {
			System.out.println("It is not possible to pack "+goal+" kg's of flour in ("+bigBag+")bigBags and ("+smallBag+")smallBags");
		}
		sc.close();
	}
	public static boolean canPack(int bigCount,int smallCount,int goal) {
		while(goal>5 && bigCount>0) {
			goal-=5;
			bigCount-=1;
		}
		while(goal>0 && smallCount>0) {
			goal-=1;
			smallCount-=1;
		}
		if(goal>0) {
			return false;
		}
		return true;
	}

}
