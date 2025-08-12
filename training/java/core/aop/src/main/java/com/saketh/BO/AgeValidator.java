package com.saketh.BO;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class AgeValidator {
//	public void validator() {
//		System.out.println("Validating age...");
//	}
	@CustomAnnotation
	public Boolean validate(int age) throws Exception {
		if(age<18) {
			throw new Exception("Invalid age exception");
		}else {
			System.out.println("This is a valid age");
		}
		return true;
	}

}
