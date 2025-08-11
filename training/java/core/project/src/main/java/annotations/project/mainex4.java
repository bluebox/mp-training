package annotations.project;
import annotations.project.vehex4;
import annotations.project.projectconfigex4;

import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class mainex4 {
    public static void main(String[] args) {

    	
    		var context = new AnnotationConfigApplicationContext(projectconfigex4.class);
//    		vehex4 vehicle1 = context.getBean(vehex4.class);
//    		System.out.println("component vehicle name from " +"spring context is : "+ vehicle1.getName());
//    		vehex4 vehex4 = new vehex4();
//			vehex4.printHello();
//    		context.close();
    		Vehicle volkswagen = new Vehicle();
    		volkswagen.setName("volkswagen");
    		Supplier<Vehicle> volkswagenSupplier =() -> volkswagen;
    		Supplier<Vehicle> audiSupplier=() -> {
    			Vehicle audi = new Vehicle();
    			audi.setName("Audi");
    			return audi;
    
    		};
    		Random random = new Random();
    		int randomNumber = random.nextInt(10);
    		System.out.println("randomNumber= " + randomNumber);
    		 if((randomNumber% 2) == 0){
    	            context.registerBean("volkswagen",
    	                    Vehicle.class,volkswagenSupplier);
    	        }else{
    	            context.registerBean("audi",
    	                    Vehicle.class,audiSupplier);
    	        }
    	        Vehicle volksVehicle = null;
    	        Vehicle audiVehicle = null;
    	        try {
    	            volksVehicle = context.getBean("volkswagen",Vehicle.class);
    	        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
    	            System.out.println("Error while creating Volkswagen vehicle");
    	        }
    	        try {
    	            audiVehicle = context.getBean("audi",Vehicle.class);
    	        }catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
    	            System.out.println("Error while creating Audi vehicle");
    	        }

    	        if(null != volksVehicle){
    	            System.out.println("Programming Vehicle name from Spring Context is: " + volksVehicle.getName());
    	        }else{
    	            System.out.println("Programming Vehicle name from Spring Context is: " + audiVehicle.getName());
    	        }

    	    }
}