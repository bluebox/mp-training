package inheritance;

import java.time.LocalDate;
import java.time.Period;

public class Worker {
 private String name;
 private String birthYear;
 private String endDate;
 public Worker(String name, String birthYear, String endDate) {
	 this.name=name;
	 this.birthYear= birthYear;
	 this.endDate = endDate;
 }
 public  int getAge() {
	 LocalDate birth = LocalDate.parse(birthYear);
	 return Period.between(birth, LocalDate.now()).getYears();
 }
 public double collectPay() {
	 return  0.0;
 }
 public void terminate(String endDate) {
	 System.out.println("Terminated on : "+ endDate);
 }
}
