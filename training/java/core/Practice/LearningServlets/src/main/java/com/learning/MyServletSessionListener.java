package com.learning;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyServletSessionListener implements HttpSessionAttributeListener{
	
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("Attribute is added for the session");
	}

}
