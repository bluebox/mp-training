import java.time.LocalDate;
import java.time.Period;

public class Worker {
	public String name;
	public String birthDate;
	public String endDate;
	public Worker(String name,String birthDate,String endDate) {
		this.name=name;
		this.birthDate=birthDate;
		this.endDate=endDate;
	}
	public int getAge() {
		LocalDate d=LocalDate.parse(birthDate);
		Period x = Period.between(d,LocalDate.now());
		return x.getYears();
	}
	public double collectPay() {
		return 0;
	}
	public void terminate() {
		System.out.println("Thanks "+name+" for your valuable services. But I am sorry to say this that you are terminated");
		
	}
}
