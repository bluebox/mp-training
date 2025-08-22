package mymaven;


import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;
import java.util.function.Supplier;

public class Cars {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Vehi volkswagen = new Vehi();
        volkswagen.setName("Volkswagen");
        Supplier<Vehi> volkswagenSupplier = () -> volkswagen;


        Supplier<Vehi> audiSupplier = () -> {
            Vehi audi = new Vehi();
            audi.setName("Audi");
            return audi;
        };

        Random random = new Random();
        int randomNumber = random.nextInt(10);
        System.out.println("randomNumber = " + randomNumber);

        if((randomNumber% 2) == 0){
            context.registerBean("volkswagen",
                    Vehi.class,volkswagenSupplier);
        }else{
            context.registerBean("audi",
                    Vehi.class,audiSupplier);
        }
        Vehi volksVehicle = null;
        Vehi audiVehicle = null;
        try {
            volksVehicle = context.getBean("volkswagen",Vehi.class);
        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
            System.out.println("Error while creating Volkswagen vehicle");
        }
        try {
            audiVehicle = context.getBean("audi",Vehi.class);
        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
            System.out.println("Error while creating Audi vehicle");
        }

        if(null != volksVehicle){
            System.out.println("Programming Vehicle name from Spring Context is: " + volksVehicle.getName());
        }else{
            System.out.println("Programming Vehicle name from Spring Context is: " + audiVehicle.getName());
        }
      context.close();
    }
}
