package com.tulasidhar.june26;

//Problem in short:
//implement simple english chars to nato representations for letters
// A, B, C, D and E . If any other char is found print "not found"

public class ChallengeSwitchNatoAlphabet {
	
	public static void main(String[] args) {
		String str = "CAR";
		for(int i=0 ; i<str.length() ; i++) {
			switch(str.charAt(i)) {
				case('A'):
					System.out.println("Able");
					break;
				case('B'):
					System.out.println("Baker");
					break;
				case('C'):
					System.out.println("Charlie");
					break;
				case('D'):
					System.out.println("Dog");
					break;
				case('E'):
					System.out.print("Easy");
					break;
				default:
					System.out.println("Not Found");
			}
		}
		
		
	}
}
