package com.saketh.Advbeans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyClass {
	LazyClass(){
		System.out.println("This is lazy class");
	}
}
