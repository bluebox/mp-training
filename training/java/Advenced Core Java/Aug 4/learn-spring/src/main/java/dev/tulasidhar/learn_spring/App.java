package dev.tulasidhar.learn_spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        
        Human human = (Human) context.getBean("humanBean");
        human.speak();
        
        Human primaryHumanBean = context.getBean(Human.class);
        primaryHumanBean.speak();
   
        Turtle turt1 = (Turtle)context.getBean("fastTurtle");
        turt1.describe();
        
        Turtle turt2 = context.getBean("slowTurtle" , Turtle.class);
        turt2.describe();
    }
}


