package Domain;

import java.util.HashMap;
import java.util.Map;

public enum IssueStatus {
      ISSUED('I'),
      RETURNED('R');
	   char string;

	IssueStatus(char string) {
		this.string=string;
	}
	
	 public char getType() {
	        return this.string;
	    }
	
	private static final Map<Character,IssueStatus> lookup=new HashMap<>();
	
	static {
		for(IssueStatus issueStatus:IssueStatus.values()) {
			lookup.put(issueStatus.getType(), issueStatus);
		}
	}
	
	public static IssueStatus getIssueStatus(char status) {
		return lookup.get(status);
	}

 
      
}



