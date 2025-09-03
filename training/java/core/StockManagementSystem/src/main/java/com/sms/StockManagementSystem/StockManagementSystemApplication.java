package com.sms.StockManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.sms"})
public class StockManagementSystemApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(StockManagementSystemApplication.class, args);
	}
//	@Bean
//    public CommandLineRunner run(SupplierServiceImplementation supplierService) {
//        return args -> {
//            Supplier supplier = new Supplier();
//            supplier.setName("vejas sai");
//            supplier.setGender("M");
//            supplier.setMobile(9876543414L);
//            supplier.setEmail("vejassai@example.com");
//            supplier.setCountry("India");
//            supplier.setState("Telangana");
//            supplier.setCity("Hyderabad");
//            supplier.setAddress("123 Street");
//            supplier.setCreatedBy("user");
//
//            Long generatedId = supplierService.addSupplier(supplier);
//            System.out.println("Inserted Supplier ID: " + generatedId);
//           
//        };
//	}
}

