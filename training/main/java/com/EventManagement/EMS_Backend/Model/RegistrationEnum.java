package com.EventManagement.EMS_Backend.Model;

import java.util.HashMap;
import java.util.Map;

public enum RegistrationEnum {
	       REGISTERED("registered"),
	       CANCELLED("cancelled");
		  String string;

		  RegistrationEnum(String string) {
			this.string=string;
		}
		
		 public String getType() {
		        return this.string;
		    }
		
	private static final Map<String,RegistrationEnum> lookup=new HashMap<>();
		
		static {
			for(RegistrationEnum registrationstatus:RegistrationEnum.values()) {
				lookup.put(registrationstatus.getType(),registrationstatus );
			}
		}
		
		public static RegistrationEnum getRegistrationstatus(String status) {
			return lookup.get(status);
		}
		
	}