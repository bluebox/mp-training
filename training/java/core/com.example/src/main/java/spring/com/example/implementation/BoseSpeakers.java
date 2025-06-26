package spring.com.example.implementation;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import spring.com.example.interfaces.Speaker;


@Component
@Primary

public class BoseSpeakers implements Speaker {
	String name="bose speaker";
	@Override
	public String makeSound() {
	return "music from bose speaker";
}
}
