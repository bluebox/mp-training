package day12.inheritencechallenge;

public class Worker {

	
	String name,birthDate,endDate;
	
	Worker(String name,String birthDate,String endDate){
		this.name=name;
		this.birthDate=birthDate;
		this.endDate=endDate;
	}
	public String getAge() {
		return  AgeCalculator.calculateAge(birthDate);
	}
	
	public void terminate(String endDate) {
		this.endDate=endDate;
		System.out.println("workers employment is terminated ");
	}
}
