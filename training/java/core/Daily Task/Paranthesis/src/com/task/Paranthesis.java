package com.task;

import java.util.Scanner;

public class Paranthesis {
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		int i=0;
		boolean x=true;
		try {
			while(s.length()>0) {
				if(s.charAt(i)=='}') {
					if(s.charAt(i-1)=='{') {
						s=s.substring(0, i-1)+s.substring(i+1,s.length());
						i-=2;
						System.out.println(s);
					}
					else {
						x=false;
						break;
					}
				}
				else if(s.charAt(i)==']') {
					if(s.charAt(i-1)=='[') {
						s=s.substring(0, i-1)+s.substring(i+1,s.length());
						i-=2;
						System.out.println(s);
					}
					else {
						x=false;
						break;
					}
				}
				else if(s.charAt(i)==')') {
					if(s.charAt(i-1)=='(') {
						s=s.substring(0, i-1)+s.substring(i+1,s.length());
						i-=2;
						System.out.println(s);
					}
					else {
						x=false;
						break;
					}
				}
				else if(s.charAt(i)=='>') {
					if(s.charAt(i-3)=='/') {
						if(s.charAt(i-8)=='<') {
							s=s.substring(0, i-8)+s.substring(i+1,s.length());
							i-=9;
							System.out.println(s);
						}
						else {
							x=false;
							break;
						}
					}
					else {
						i+=1;
						continue;
					}
				}
				else {
					i+=1;
					continue;
				}
				i+=1;
				
			}
			if(x==true) {
				System.out.print("Given string is valid");
			}
			else {
				System.out.print("Given string is invalid");
			}
		}
		catch(Exception e) {
			System.out.print("Given string is invalid due to error");
		}
	}
}
