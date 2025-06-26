package spring.com.example.implementation;
import org.springframework.stereotype.Component;

import spring.com.example.interfaces.Speaker;

@Component
public class SonySpeakers implements Speaker {
	String name="Sony Speakers";
	@Override
public String makeSound() {
    return	" music from sonyspeaker";
}
}
