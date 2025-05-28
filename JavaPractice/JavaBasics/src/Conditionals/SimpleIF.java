package Conditionals;

public class SimpleIF {
	public static void main(String args[]) {
		boolean isReward = true;
		int currentRewards = 0;
		
		if (isReward) {
			System.out.println("Congratulations, you have received reward points");
			currentRewards += 15;
		}
		
		if (currentRewards != 0) {
			System.out.println("Ewwww! Party");
		}
		System.out.println("Current Rewards is "+currentRewards);
	}
}
