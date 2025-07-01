package Arrays;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int [] values=MinimumElement.readIntegers();
		System.out.println("u entered"+Arrays.toString(values));
		int minValue=MinimumElement.findMin(values);
		System.out.println("minimum value is--"+minValue);
	}

}
