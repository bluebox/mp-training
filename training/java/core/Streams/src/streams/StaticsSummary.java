package streams;

import java.util.stream.IntStream;

public class StaticsSummary {
	public static void main(String[] args) {
		IntStream is=IntStream.iterate(1,i->i+1)
				.filter(i->isEven(i))
				.limit(100);
		System.out.println(is.summaryStatistics());
	}
	public static boolean isEven(int a)
	{
		return a%2==0;
	}

}
