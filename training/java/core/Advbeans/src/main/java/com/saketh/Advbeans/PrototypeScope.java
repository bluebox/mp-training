package com.saketh.Advbeans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
public class PrototypeScope {
	static int count=0;
	PrototypeScope(){
		System.out.println(count++);
	}
}
