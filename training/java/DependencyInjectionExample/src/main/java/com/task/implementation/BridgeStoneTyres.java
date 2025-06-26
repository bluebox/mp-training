package com.task.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.task.interfaces.Tyres;


@Component
@Primary
public class BridgeStoneTyres implements Tyres{
	public String rotate() {
		return "bridge stone tyres rotating";
	}
}
