package Weekproblems;

import java.util.HashMap;
import java.util.Map;

public class Dailyproblem_romantoint {
	
	
	
	public static void main(String[] args) {
		Map<Character,Integer> map=new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		System.out.println(romantoint("ix",map));
		
		
		
	}
	public static int romantoint(String roman,Map<Character,Integer> map) {
		roman=roman.toUpperCase();
		int num=0;
		int romnSize=roman.length();
		for(int i=1;i<romnSize;i++) {
			
			if(map.get(roman.charAt(i))<=map.get(roman.charAt(i-1))) {
				num+=map.get(roman.charAt(i-1));
			}
			else {
				num-=map.get(roman.charAt(i-1));
			}
			
			if(i==romnSize-1) {
				num+=map.get(roman.charAt(romnSize-1));

			}
			
		}
//		num+=map.get(roman.charAt(roman.length()-1));
		if(num<0) {
			System.out.println("invalid String");
			return -1;
		}
		return num;
		
		
	}

}
