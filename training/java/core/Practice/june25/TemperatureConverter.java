package Practice.june25;

import java.util.Scanner;

/*Temperature Converter
Create a method that takes temperature in Celsius as a parameter and returns the temperature in Fahrenheit using the formula:
F = (C * 9/5) + 32
Then in main, use if to print:
"Freezing" if temp < 32°F
"Boiling" if temp > 212°F
"Normal" otherwise.*/

public class TemperatureConverter {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter temperature in celcius");
		int c=sc.nextInt();
		float temp=toFahrenheit(c);
		System.out.println("The fahrenheit temp of given temp "+c+"C is: "+temp+"F");
		if(temp<32) {
			System.out.print("The weather condition is: Freezing");
		}
		else if(temp >212) {
			System.out.print("The weather condition is: Boiling");
		}
		else {
			System.out.print("The weather condition is: Normal");
		}
		sc.close();
	}
	public static float toFahrenheit(int c) {
		int f=(c*9/5)+32;
		return f;
	}
}
