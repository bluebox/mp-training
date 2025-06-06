package Jun5;

import java.util.*;


public class DicePlayer {
	
	private Map<String,Integer> scoreCard=new LinkedHashMap<>();
	private Set<String> usedCategories=new HashSet<>();
	
	private static final List<String> categories=Arrays.asList("ACES","TWOS","THREES","FOURS");
	
	public void score(int[] diceRoll) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Select category");
		for(String category:categories) {
			if(!usedCategories.contains(category)) {
				System.out.println(category);
			}
		}
		
		String selectedCategory="";
		while(true) {
			selectedCategory=sc.nextLine().trim().toUpperCase();
			if(!usedCategories.contains(selectedCategory) && categories.contains(selectedCategory)) {
				break;
			}
			System.out.println("ALready used");
		}
		
		
		int score=calculateScoreForCategory(selectedCategory,diceRoll);
		
		scoreCard.put(selectedCategory, score);
		usedCategories.add(selectedCategory);
		
		
		System.out.println("Scores: "+score+" points in category "+selectedCategory);
		
	}
	private int calculateScoreForCategory(String category,int[] dice) {
		int score=0;
		switch(category) {
		case "ACES" :
			score=countAndMultiply(dice,1);
			break;
		case "TWOS":
			score=countAndMultiply(dice,2);
			break;
		case "THREES":
			score=countAndMultiply(dice,3);
			break;
		case "FOURS":
			score=countAndMultiply(dice,4);
			break;
		default: score=0;
		}
		return score;
	}
	
	private int countAndMultiply(int[] dice,int face) {
		int count=0;
		for(int die:dice) {
			if(die==face)
				count++;
			
		}
		return count*face;
	}
	
	public static void main(String args[]) {
		DicePlayer player=new DicePlayer();
		int[] diceRoll= {1,1,3,4,6};
		player.score(diceRoll);
	}
}
