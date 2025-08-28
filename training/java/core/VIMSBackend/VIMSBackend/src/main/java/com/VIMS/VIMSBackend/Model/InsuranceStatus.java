package com.VIMS.VIMSBackend.Model;

import java.util.HashMap;
import java.util.Map;


public enum InsuranceStatus {
	   INACTIVE("I"),
	      ACTIVE("A");
		   String string;

		InsuranceStatus(String string) {
			this.string=string;
		}
		
		 public String getType() {
		        return this.string;
		    }
		
		private static final Map<String,InsuranceStatus> lookup=new HashMap<>();
		
		static {
			for(InsuranceStatus issueStatus:InsuranceStatus.values()) {
				lookup.put(issueStatus.getType(), issueStatus);
			}
		}
		
		public static InsuranceStatus getIssueStatus(String status) {
			return lookup.get(status);
		}

}
