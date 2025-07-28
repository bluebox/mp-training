package Domain;

import java.util.HashMap;
import java.util.Map;

public enum BookAvailability {
       AVAILABLE('A'),
       ISSUED('I');
	  char string;

	BookAvailability(char string) {
		this.string=string;
	}
	
	 public char getType() {
	        return this.string;
	    }
	
private static final Map<Character,BookAvailability> lookup=new HashMap<>();
	
	static {
		for(BookAvailability bookAvailability:BookAvailability.values()) {
			lookup.put(bookAvailability.getType(),bookAvailability );
		}
	}
	
	public static BookAvailability getAvailability(char Availability) {
		return lookup.get(Availability
				);
	}
	
}
