package problems;

public class Day_2_Calculator {
    
	private  long number_1;
	private  long number_2;
	
	public Day_2_Calculator(long num,long num2) {
		number_1=num;
		number_2=num2;
	}
	
	public Day_2_Calculator() {
		
	}
	
	public Long getNumber_1() {
		return number_1;
	}
	public void setNumber_1(Long number_1) {
		this.number_1 = number_1;
	}
	public long getNumber_2() {
		return number_2;
	}
	public void setNumber_2(long number_2) {
		this.number_2 = number_2;
	}
	
	public long getAdditionResult() {
		return number_1+number_2;
	}
	  
	public long getSubtractionResult() {
		return number_1-number_2;
	} 
	
	public long getMultiplicationResult() {
		return number_1*number_2;
	}
	
	public long getDivisionResult() {
		if(number_2==0)return 0;
		return number_1/number_2;
	}
}
