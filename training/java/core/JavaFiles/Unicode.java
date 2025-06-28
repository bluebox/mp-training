
public class Unicode {
	public static void main(String[] args) {
	
		char myChar = 'A';
		char myUniChar = '\u0041'; // A-Z = 0041 - 005A (Y= 0059) a-z = 0062 - 007A
		char myAsciiChar = 65; // A-Z = 65 - 90 a-z = 97 - 122
		
		System.out.println(myChar+" " + myUniChar+ " " +myAsciiChar);

	}
}
