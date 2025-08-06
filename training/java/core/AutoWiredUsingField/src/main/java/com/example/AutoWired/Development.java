package com.example.AutoWired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class Development {
@Autowired	
Laptop hp;

//public Development(Laptop hp) {
//	super();
//	this.hp = hp;
//}
public Laptop getHp() {
	return hp;
}
public void setHp(Laptop hp) {
	this.hp = hp;
}
public void build() {
//hp.compile(); 

System.out.println("building project");
}

}
