package challenges;

import java.util.function.Consumer;

public class MiniChallenge1 {
	
	
	public static void main(String[] args) {
		Consumer<String> printExample = S->
		{
			String[] strings = S.split(" ");
			for(String s:strings)
			{
				System.out.println(s);
			}
		};
		
		printExample.accept("Hello world. How do you do");
	}

}
