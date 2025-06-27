package Day1_prctice;

public class GradingSystem {
	//assigning grades according to value
	public static void main(String args[]) {
		int marks=78;
		char grade='D';
		if(marks>90)
			grade='O';
		if(marks>80 && marks<=90)
			grade='A';
		if(marks>70 && marks<=80)
			grade='B';
		if(marks>60 && marks<=70)
			grade='C';
		
		System.out.println("Grade is :"+ grade);
	}
}
