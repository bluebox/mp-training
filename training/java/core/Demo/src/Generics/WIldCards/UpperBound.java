package Generics.WIldCards;
import java.util.Arrays;
import java.util.List;

public class UpperBound {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> integers = Arrays.asList(1, 2, 3);
	    System.out.println("Sum of integers: " + sumOfList(integers));

	    List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);
	    System.out.println("Sum of doubles: " + sumOfList(doubles));

	}
	
	public static double sumOfList(List<? extends Number> numList) {
		double sum=0;
		for (Number num: numList) {
			sum+=num.doubleValue();
		}
		return sum;
	}

}
