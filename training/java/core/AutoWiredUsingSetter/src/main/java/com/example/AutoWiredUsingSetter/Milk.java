package com.example.AutoWiredUsingSetter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Milk {
 Tea tea;

public Tea getTea() {
	return tea;
}
//@Autowired
//public Milk(Tea tea) {
//	super();
//	this.tea = tea;
//}
@Autowired
public void setTea(Tea tea)
{
	this.tea=tea;
}

public void prepere()
{
	System.out.println("tea is preparing with milk");
	
}
}
