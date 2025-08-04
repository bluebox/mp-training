package Domain;

import java.util.HashMap;
import java.util.Map;

public enum BookStatus {
   ACTIVE("A"),
   INACTIVE("I");
	String string;

BookStatus(String string) {
	this.string=string;
}

public String getType() {
    return this.string;
}

private static final Map<String,BookStatus> lookup=new HashMap<>();

static {
	for(BookStatus bookStatus:BookStatus.values()) {
		lookup.put(bookStatus.getType(), bookStatus);
	}
}

public static BookStatus getStatus(String status) {
	return lookup.get(status);
}
   
}
