package com.springcore.PersonVehicle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcore.model.BeanConfig;
import com.springcore.model.Person;
import com.springcore.model.Vehicle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context= new AnnotationConfigApplicationContext(BeanConfig.class);
        Person p1=context.getBean(Person.class);
        p1.setPersonName("Jhon");
        Vehicle v1=context.getBean(Vehicle.class);
        v1.setVehicleName("creta");
        p1.setCar(v1);
        p1.hascar();
    }
}
