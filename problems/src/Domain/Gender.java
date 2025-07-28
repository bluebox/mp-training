package Domain;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
     MALE('M'),
     FEMALE('F');
    char string;
	
	Gender(char string) {
		this.string=string;
	}
	
	 public char getType() {
	        return this.string;
	    } 
	
	
private static final Map<Character,Gender> lookup=new HashMap<>();
	
	static {
		for(Gender gender:Gender.values()) {
			lookup.put(gender.getType(),gender);
		}
	}
	
	public static Gender getGender(char c) {
		return lookup.get(c);
	}
	
}
