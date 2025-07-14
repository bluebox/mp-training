package July8;

import java.util.Random;

public class Dice {
	
	public static void main(String[] args) {
		
		int p1 = 0, p2 = 0;
		int turns = 0;
		int wins = 5;
		
		Random r = new Random();
		
		while(p1 < wins && p2 < wins) {
			turns ++;
			if(turns % 2 == 1) {
				p1 += r.nextInt(1,7);
				System.out.println("Turn " + turns + " p1 = " + p1);
			}else {
				p2 += r.nextInt(1,7);
				System.out.println("Turn " + turns + " p2 = " + p2);
			}
		}
		if(p1 >= wins) {
			System.out.println("p1 wins");
		}else {
			System.out.println("p1 wins");
		}
	}
}

