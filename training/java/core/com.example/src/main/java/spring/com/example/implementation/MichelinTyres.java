package spring.com.example.implementation;
import org.springframework.stereotype.Component;

import spring.com.example.interfaces.Tyres;

@Component
public class MichelinTyres implements Tyres{
	String name="Michelin tyres";
@Override
public String rotate() {
	return " vehicle with michelin tyres";
}
}
