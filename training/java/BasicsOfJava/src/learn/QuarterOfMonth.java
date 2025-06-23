package learn;

public class QuarterOfMonth {
	public static void main(String[] args) {
		System.out.println(getQuarter("September"));
	}
	
	public static String getQuarter(String month) {
		return switch(month) {
			case "january","february","march" ->"1";
			case "april","may","june" ->"2";
			case "july","August","September" ->"3";
			case "october","nov","dec" ->"4";
			default -> "Not valid";
		};
	}
}
