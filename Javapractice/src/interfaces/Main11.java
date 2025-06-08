package interfaces;
import java.util.List;

public class Main11 {
	
	public static void main(String[] args) {
		
	
	Player tim = new Player("Tim", 10, 15);
    System.out.println("Initial Player: " + tim);

    List<String> timData = tim.write();
    System.out.println("Player Data (written): " + timData);
    
    Monster werewolf = new Monster("Werewolf", 20, 40);
    System.out.println("\nInitial Monster: " + werewolf);

    List<String> werewolfData = werewolf.write();
    System.out.println("Monster Data (written): " + werewolfData);
}
}