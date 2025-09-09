package com.Day2_;

public class Play_Cat {
	public static void main(String[]args) {
		System.out.print(iscatplaying(false, 20));
		System.out.print(iscatplaying(true, 36));
		System.out.print(iscatplaying(true, 35));
	}
	public static boolean iscatplaying(boolean summer, int temperature) {
		if (summer == true && temperature >20 && temperature <45) {
			return true;
		}else {
			return false;
		}
			}

}
