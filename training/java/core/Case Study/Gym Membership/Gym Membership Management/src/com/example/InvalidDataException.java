package com.example;

public class InvalidDataException extends RuntimeException{
	public InvalidDataException(String message){
		super(message);
	}
}
