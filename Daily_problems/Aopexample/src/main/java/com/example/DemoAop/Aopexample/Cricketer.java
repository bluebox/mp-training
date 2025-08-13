package com.example.DemoAop.Aopexample;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Cricketer {
	public Cricketer() {
		System.out.println("this is during the execution of cricketer");
	}
	
	public void cricket() {
		System.out.println("playing  cricket is fun .....");
	}
	public void cricketexception() {
		int a=10/0;
	}
	

}
@Component
class Virat{
	public Virat() {
		System.out.println("this is before the creation of object");
	}
}
