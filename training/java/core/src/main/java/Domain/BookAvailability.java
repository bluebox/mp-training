package Domain;

import java.util.HashMap;
import java.util.Map;

public enum BookAvailability {
       AVAILABLE("A"),
       ISSUED("I");
	  String string;

	BookAvailability(String string) {
		this.string=string;
	}
	
	 public String getType() {
	        return this.string;
	    }
	
private static final Map<String,BookAvailability> lookup=new HashMap<>();
	
	static {
		for(BookAvailability bookAvailability:BookAvailability.values()) {
			lookup.put(bookAvailability.getType(),bookAvailability );
		}
	}
	
	public static BookAvailability getAvailability(String Availability) {
		return lookup.get(Availability
				);
	}
	
}
