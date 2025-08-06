package com.example.AutoWireUsingConstructor;

import org.springframework.stereotype.Component;

@Component
public class Father {
	private int age;
private final Son son;

public Father(Son son) {
	this.son = son;
}
public void  Father(Son son1,Son son2)
{
	
	
}
public Son getSon() {
	return son;
}
public void Scold()
{
	System.out.println("Father started scolding");
}

}
