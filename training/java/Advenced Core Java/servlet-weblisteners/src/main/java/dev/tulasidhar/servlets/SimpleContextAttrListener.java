package dev.tulasidhar.servlets;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class SimpleContextAttrListener implements ServletContextAttributeListener {

	
    public void attributeAdded(ServletContextAttributeEvent scae)  { 
         System.out.println("A new servlet context is added");
    }

	
    public void attributeRemoved(ServletContextAttributeEvent scae)  { 
         System.out.println("attribute removed");
    }

	
    public void attributeReplaced(ServletContextAttributeEvent scae)  { 
        System.out.println("One attribute is replaced : "+scae.getName());
    }
	
}
