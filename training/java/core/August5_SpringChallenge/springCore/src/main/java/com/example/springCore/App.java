package com.example.springCore;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.springCore.config.AppConfig;
import com.example.springCore.model.Person;
import com.example.springCore.model.Student;

public class App 
{
    public static void main( String[] args )
    {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
			Person person = context.getBean(Person.class);

		       person.getVehicle().getVehicleServices().playMusic();
			person.getVehicle().getVehicleServices().moveVehicle();
			
			//Student s=context.getBean(Student.class);
		} catch (BeansException e) {
			e.printStackTrace();
		}
    }
    }

