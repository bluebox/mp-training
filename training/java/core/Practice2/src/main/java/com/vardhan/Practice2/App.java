package com.vardhan.Practice2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
  public static void main(String[] args) {
    
	  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
	  Pojo obj= context.getBean(Pojo.class);
	  obj.setName("vardhan");
	  System.out.println(obj.getName());
	  Service service = context.getBean(Service.class);
	  context.registerBean(Register.class);
	  Register objRegister = context.getBean(Register.class);
	  objRegister.getNumber();
	  System.out.println(objRegister);
	  context.close();
	  
  }
}