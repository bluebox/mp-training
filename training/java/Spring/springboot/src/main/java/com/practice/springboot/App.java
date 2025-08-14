package com.practice.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.practice.springboot.domain.Alien;

@SpringBootApplication
public class App 
{

	public static void main( String[] args )
    {
		 ConfigurableApplicationContext context = SpringApplication.run(App.class);
		 
		 Alien a = context.getBean(Alien.class);
		 a.show();
//		 a.setAid(1001);
//		 System.out.println(a.getAid());
//		 Alien a1 = context.getBean(Alien.class);
//		 a1.show();
//		 System.out.println(a1.getAid());
    }
}
