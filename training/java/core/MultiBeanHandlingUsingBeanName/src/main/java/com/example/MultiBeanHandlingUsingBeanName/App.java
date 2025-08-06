package com.example.MultiBeanHandlingUsingBeanName;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      
       ApplicationContext cxt=new AnnotationConfigApplicationContext(NotificationConfig.class);
      Notification  nc= cxt.getBean("emailNotification" ,Notification.class);
      
      System.out.println(nc.getMessage());

    }
}
