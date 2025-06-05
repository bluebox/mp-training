package myStreams;

import java.util.Optional;


public class MyOptional {
	public static Optional<String> isGreater(int number) {
		if(number == 100)
			return Optional.of("100");
		return Optional.ofNullable(null);
	}
	public static void main(String [] args) {
		Optional<String> optional1 = Optional.of("Java");
		Optional<String> optional2 = Optional.ofNullable(null);
		Optional<String> optional3 = Optional.empty();
	if(optional1.isPresent()) {
		System.out.println("Optional 1 value is "+ optional1.map(s ->s.toUpperCase()).get());
	}
	Optional<String> optional4= isGreater(10);
	System.out.println(optional4.orElse("Not 100"));
	System.out.println("Optional 2 value is "+optional2.orElse("Optional 2 is nullable"));
	System.out.println("optional 3 is " + optional3.orElse("nullable"));
	}
	
}

