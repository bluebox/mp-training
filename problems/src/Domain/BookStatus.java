package Domain;

import java.util.HashMap;
import java.util.Map;

public enum BookStatus {
   ACTIVE('A'),
   INACTIVE('I');
	char string;

BookStatus(char string) {
	this.string=string;
}

public char getType() {
    return this.string;
}

private static final Map<Character,BookStatus> lookup=new HashMap<>();

static {
	for(BookStatus bookStatus:BookStatus.values()) {
		lookup.put(bookStatus.getType(), bookStatus);
	}
}

public static BookStatus getStatus(char status) {
	return lookup.get(status);
}
   
}
