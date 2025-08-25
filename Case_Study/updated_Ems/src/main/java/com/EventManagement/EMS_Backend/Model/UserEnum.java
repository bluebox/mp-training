package com.EventManagement.EMS_Backend.Model;


import java.util.HashMap;
import java.util.Map;

public enum UserEnum {




	       ADMIN("Admin"),
	       STUDENT("Student"),
			FACULTY("Faculty");
		  String string;

		  UserEnum(String string) {
			this.string=string;
		}
		
		 public String getType() {
		        return this.string;
		    }
		
	private static final Map<String,UserEnum> lookup=new HashMap<>();
		
		static {
			for(UserEnum userType:UserEnum.values()) {
				lookup.put(userType.getType(),userType );
			}
		}
		
		public static UserEnum getUsertype(String usertype) {
			return lookup.get(usertype);
		}
		
	}