package myRandamization;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MyBigDecimal {
	public static void main(String[] args) {
		BigDecimal myBigDecimal= new BigDecimal("10.08625");
		BigDecimal myBigDecimal2= new BigDecimal("1090.08625");
		System.out.println(myBigDecimal.add(myBigDecimal2));
		System.out.println(myBigDecimal.subtract(myBigDecimal2));
		BigDecimal rounded = myBigDecimal.setScale(2, RoundingMode.HALF_UP);
		System.out.println(rounded);
		BigDecimal xVariable = new BigDecimal("5.00");
		BigDecimal yVariable = new BigDecimal("5.0");
		System.out.println(xVariable.equals(yVariable));
		System.out.println(xVariable.compareTo(yVariable));
		List<BigDecimal> myList = new ArrayList<>();
		myList.add(myBigDecimal);
		myList.add(myBigDecimal2);
		myList.add(xVariable);
		myList.add(yVariable);
		myList.add(rounded);
		myList.stream()
		.map(s->s.setScale(2,RoundingMode.DOWN))
		.forEach(System.out::println);
	}
	
}
