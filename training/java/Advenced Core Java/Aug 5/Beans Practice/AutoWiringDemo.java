package dev.tulasidhar.aug5;

import org.springframework.beans.factory.annotation.Autowired;

public class AutoWiringDemo {
	@Autowired
	static SimpleObject obj;
	
	public static void main(String[] args) {
		obj.doSimpleThing();
	}
	
}
