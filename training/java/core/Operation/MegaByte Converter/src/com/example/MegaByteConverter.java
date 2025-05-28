package com.example;

import java.util.Scanner;

public class MegaByteConverter {
	public static void printMegaBytesConverter(int kb) {
		if(kb<0) {
			System.out.print("Invalid Value");
		}
		else if(kb>=1024) {
			System.out.println(kb/1024+" MB and "+kb%1024+" KB");
		}
		else {
			System.out.println(kb+" KB");
		}
	}
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		printMegaBytesConverter(sc.nextInt());
	}
}
