package com.example;

public class TypeCasting {
	public static void main(String arg[]) {
		double a=554654657.537976543d;
		int i=(int) a;
		short s=(short) a;
		byte b=(byte) a;
		long l=(long) a;
		long l1=568743567l;
		float f=(float) a;
		float f1=2457.84356789054673245678923456798f;
		char c=(char) a;
		char c1=Character.MIN_VALUE;
		char c2=Character.MAX_VALUE;
		System.out.println(s);
		System.out.println("Double value of is "+a);
		System.out.println("Short value of "+a+" is "+s);
		System.out.println("Integer value of "+a+" is "+i);
		System.out.println("Byte value of "+a+" is "+b);
		System.out.println("Long value of "+a+" is "+l);
		System.out.println("float value of "+a+" is "+f);
		
}}
