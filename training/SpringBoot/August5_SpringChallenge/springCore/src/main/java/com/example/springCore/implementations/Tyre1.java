package com.example.springCore.implementations;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.springCore.interfaces.Tyres;

@Component
@Primary
public class Tyre1 implements Tyres{
			@Override
	public String rotate() {
		return "Tyre1 is rotating";
	}

}
