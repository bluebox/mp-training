package CheckingWhetherThreeNumbersAreSame;

public class CheckThreeNumbers {

	public static void main(String[] args) {
		int numberOne=3,numberTwo=3,numberThree=3;
		compareThreeNumbers(numberOne,numberTwo,numberThree);
	}
	public static void compareThreeNumbers(int numOne,int numTwo,int numThree)
	{
		String text= (numOne == numTwo ? 
				(numTwo == numThree ? " all are same" : " neither all same nor all different")
				:
				(numTwo == numThree ? " neither all same nor all different": " all are different")
				);
		System.out.print(numOne+ ", "+numTwo+ ", "+numThree +text);
				
	}

}
