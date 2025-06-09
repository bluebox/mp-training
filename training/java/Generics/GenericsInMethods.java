package Generics;
public class GenericsInMethods {
	public static <T> void swap(T i, T j) {
		System.out.println("Before swap :" +i+ " -> "+j);
		T temp;
		temp=i;
		i=j;
		j=temp;
		System.out.println("After swap :" +i+ " -> "+j);
	}
	public static void main(String[] args) {
		swap(1,2);
		swap("ab","ba");
		swap(true,false);
	}
}
