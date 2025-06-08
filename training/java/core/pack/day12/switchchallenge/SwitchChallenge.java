package day12.switchchallenge;

public class SwitchChallenge {

    public static void main(String[] args) {
	        char switchChar = 'E';
	        switch (switchChar){
                case 'A': case 'B': case 'C': case 'D': case 'E':
                    System.out.println("The char you typed in was " + switchChar);
                    break;
                default:
                    System.out.println("Char 'A','B','C', 'D' or 'E' was not found ");
                    break;
            }
    }
}
