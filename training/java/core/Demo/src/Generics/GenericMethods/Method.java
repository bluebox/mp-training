package Generics.GenericMethods;

public class Method {
	
	public static <E> void printArray(E[] array) {
		for(E ele: array) {
			System.out.println(ele);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Integer[] intArr = {1,2,3,4,5};
		Double[] douArr= {1.1,2.2,3.3,4.4};
		Character[] charArr= {'a','b','c','r'};
		
		printArray(intArr);
		printArray(douArr);
		printArray(charArr);

	}

}
