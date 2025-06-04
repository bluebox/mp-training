package Generics;
import java.util.function.Function;


public class Example<E> {
	
	static class innerClass<T,R>
	{
		public static <T,R> R function(Function<T, R> func,T value1)//The function, as it is a static method cannot access the generics related to the outer class nor the inner class.
		//hence The types must be explicitly mentioned for the function too.
		{
			return func.apply(value1);
		}
		
		
	}
	
	
	public static void main(String[] args) {
		innerClass<Integer, Integer> inner = new Example.innerClass<Integer, Integer>();
//		inner.function(n->{
//			
//		}, 10);
	}

}
