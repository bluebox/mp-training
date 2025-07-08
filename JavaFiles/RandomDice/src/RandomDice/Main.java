package RandomDice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	 private static final Random random = new Random();

	 private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
        List<Integer> currentDice = new ArrayList<>();
        
        do {
        	rollDice(currentDice);
        }while(!reRoll(currentDice));

//		random.ints(1,7).limit(5).boxed().forEach(System.out::println);
        System.out.println("Game Ends!");

	}
	
	public static void rollDice(List<Integer> currentDice) {
		
		int randomCount = 5 - currentDice.size();
        var newDice = random.ints(randomCount, 1, 7)
			                .sorted()
			                .boxed()
			                .toList();

        currentDice.addAll(newDice);

        System.out.println("You're dice are: " + currentDice);
	}
	
	private static boolean reRoll(List<Integer> currentDice) {

        String prompt = """
                    Press Enter to Stop.
                    Type "ALL" to re-roll all the dice.
                    List numbers (separated by spaces) to reRoll selected dice.
                        """;
        
        System.out.print(prompt + "-->  ");
       
        String userInput = scanner.nextLine();
        
        if (userInput.isBlank()) {
            return true;
        }
        
        try {
            removeDice(currentDice, userInput.split(" "));

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        return false;
    }
	
	public static void removeDice(List<Integer> currentDice,String[] select) {
		if(select.length==1 && select[0].contains("ALL")) {
			currentDice.clear();
		}else {
			for(String s:select) {
				currentDice.remove(Integer.valueOf(s));
			}
		}
        System.out.println("You're Remaining dice are: " + currentDice);
	}

}
