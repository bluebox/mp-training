
import java.util.*;
import java.util.stream.Collectors.*;
public class dicegame {
	
	private static final Random  random = new Random();
	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		List<Integer> currentDice = new ArrayList<>();
	
		do {
			rollDice(currentDice);
		
		}while(!pickLosers(currentDice));
        system.out.prinln("game over, final dice: " +currentDice);
		
			
		}
	
	private static void rollDice(List<Integer> currentDice) {
		int randomCount = 5 - currentDice.size();
		List<Integer> newDice = random
				.ints(randomCount,1, 7)
		        .sorted()
		        .boxed()
		        .toList();
	currentDice.addAll(newDice);
	collections.sort(currentDice);
    system.out.println("current dice: " + currentDice);

	}
    public static void removeDice(List<Integer> currentDice,string[] userInput) {
        currentDice.clear();
    } else {
        for(string s : userInput) {
            try{
            int value = Integer.parseInt(s);
            if(!currentDice.remove(Integer.valueOf(value))){
                system.out.println("value " + value + "not found in dice!");

            }
        }
            catch{

            }
        }
    }

            
        }
    
    private static boolean pickLosers(List<Integers>currentDice) {
        string prompt = """
                press Enter to score.
                Type "all" to re-roll all the dice.
                List numbers (separated by spaces) to re-roll  selected dice.
                """; 
                

    }
		
		
		
		

	