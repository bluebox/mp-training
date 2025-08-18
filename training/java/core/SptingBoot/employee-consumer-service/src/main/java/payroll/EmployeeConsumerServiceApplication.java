package payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmployeeConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeConsumerServiceApplication.class, args);
    }
}
