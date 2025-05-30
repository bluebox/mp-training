package com.example;

public class MainClass {
	public static<T extends SubClass> T sum(T x){
		return x;
	}
	public static void main(String[] args) {
		SubClass s=new SubClass(5);
		System.out.print(sum(s));
	}
}
