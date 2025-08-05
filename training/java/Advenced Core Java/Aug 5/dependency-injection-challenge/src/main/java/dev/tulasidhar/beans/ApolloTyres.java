package dev.tulasidhar.beans;

import org.springframework.stereotype.Component;

@Component
public class ApolloTyres implements Tyres {

	@Override
	public void rotate() {
		System.out.println("Apollo Tyres are rotating");
	}

}
