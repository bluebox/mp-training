package InheritanceChallange;

public class Worker {
	 private String name;
	 private String birthDate;
	 private String endDate;
	 
	 public Worker(String name, String birthDate, String endDate) {
		this.name = name;
		this.birthDate = birthDate;
		this.endDate = endDate;
	 }
	 
	 public int getAge() {
		 return ( 2025  - Integer.parseInt(birthDate) );
	 }
	 
	 public void collectPay() {
		 System.out.println(name+ " salary collected ");
	 }
	 
	 public String terminate() {
		 return endDate;
	 }

	 public String getName() {
		return name;
	 }

	 public void setName(String name) {
		this.name = name;
	 }

	 public String getBirthDate() {
		return birthDate;
	 }

	 public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	 }

	 public String getEndDate() {
		return endDate;
	 }

	 public void setEndDate(String endDate) {
		 this.endDate = endDate;
	 }
}
