package dev.tulasidhar.aug5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBeanDemo {
	
	public static void main(String[] args) {
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:Beans.xml");
			
			SimpleObject obj = context.getBean(SimpleObject.class);
			
			obj.doSimpleThing();
			
	}
}
