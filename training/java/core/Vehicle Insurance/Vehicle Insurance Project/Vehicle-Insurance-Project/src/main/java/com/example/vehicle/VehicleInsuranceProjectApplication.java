package com.example.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VehicleInsuranceProjectApplication {
//	public void run() {
//		PolicyDao p=new PolicyDao(jdbcTemplate);
//		while(true) {
//			log.info(p.dueDate());
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
////		}
//		try {
//			Class.forName(driver);
//			Connection conn = DriverManager.getConnection(url, username, password);
//			
//			while(true) {	
//				PreparedStatement ps = conn.prepareStatement("update policy set policy_status='I' where policy_status='A' and end_date>?");
//				ps.setString(1, String.valueOf(LocalDateTime.now()));
//				int x=ps.executeUpdate();
//				if(x>0) {
//					System.out.println("There are "+x+" policies that became InActive");
//				}
//				else {
//					System.out.println("There is no policy that is achieved due date");
//				}
//				Thread.sleep(5000);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(VehicleInsuranceProjectApplication.class, args);
		
	}

}
