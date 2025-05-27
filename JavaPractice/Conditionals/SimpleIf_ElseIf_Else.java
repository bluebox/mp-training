package Conditionals;

public class SimpleIf_ElseIf_Else {
	public static void main(String args[]) {
		int experience = 5;
		
		if (experience < 0) {
			System.out.println("Invalid experience... Experience should be positive");
		}
		else if (experience == 0) {
			System.out.println("Fresher");
		}
		else if (experience >= 0 && experience < 2) {
			System.out.println("Entry-Level");
		}
		else if (experience >= 2 && experience < 6) {
			System.out.println("Associate");
		}
		else {
			System.out.println("Senior");
		}
	}
}
