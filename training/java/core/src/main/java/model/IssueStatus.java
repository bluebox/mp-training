package model;

import java.util.HashMap;
import java.util.Map;

public enum IssueStatus {
      ISSUED("I"),
      RETURNED("R");
	  String string;

	IssueStatus(String string) {
		this.string=string;
	}
	
	 public String getType() {
	        return this.string;
	    }
	
	private static final Map<String,IssueStatus> lookup=new HashMap<>();
	
	static {
		for(IssueStatus issueStatus:IssueStatus.values()) {
			lookup.put(issueStatus.getType(), issueStatus);
		}
	}
	
	public static IssueStatus getIssueStatus(String status) {
		return lookup.get(status);
	}

 
      
}



