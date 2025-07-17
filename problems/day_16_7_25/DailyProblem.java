package day_16_7_25;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DailyProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter roman number in the range of 1 to 2000");
	    String roman=sc.nextLine();
	    roman=roman.toLowerCase();
		HashMap<Character,Integer> map=new HashMap<>();
		String regex = "^(M{0,1})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
       Pattern pattern=Pattern.compile(regex);
       Matcher m = pattern.matcher(roman.toUpperCase());
       
		map.put('i', 1);
		map.put('v', 5);
		map.put('x', 10);
		map.put('l', 50);
		map.put('c', 100);
		map.put('d', 500);
		map.put('m', 1000);
		
		int sum=0;
		if(m.matches()) {
		for(int i=1;i<roman.length();i++) {
			if((map.get(roman.charAt(i))<=map.get(roman.charAt(i-1)))){
				sum=sum+map.get(roman.charAt(i-1));
			}else{
				sum=sum-map.get(roman.charAt(i-1));
			}
		}
		
		sum=sum+map.get(roman.charAt(roman.length()-1));
		  System.out.println(sum);
		}else {
			System.out.println("Invalid Roman Number");
		}
	    
	  
	    
	    

	}

}
