package dev.tulasidhar.presonalpractice;


enum Day {
	Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday;
}

public class EnumPractice {
	public static void main(String[] args) {
		
		//to store an enum value:
		Day today = Day.Friday;
		System.out.println("today is "+today);
		System.out.println(Day.Monday);
	}
}
