package com.example;

import java.util.Scanner;
import java.util.stream.Stream;

public class Ranking {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		String s=Stream.of(x).map(i->i>90?"O Grade":(i>80)?"A+ Grade":(i>70)?"A Grade":(i>60)?"B+ Grade":(i>50)?"B Grade":(i>40)?"C Grade":(i>35)?"D Grade":"Fail").findAny().toString();
		System.out.println(s);
	}
}
