package com.prana;

import java.util.function.Consumer;

public class Mini1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Consumer<String>printTheParts=sentence->
		{
			String[]parts=sentence.split(" ");
			for(String part:parts)
			{
				System.out.println(part);
			}
		};
		printTheParts.accept("lets split all");
	}

}


