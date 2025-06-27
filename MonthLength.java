
public class MonthLength {

	public static boolean IsLeapYear(int year) {
		if(year >=1  && year<=9999) {
			if((year % 4 == 0 && year%100 != 0) || year%400 == 0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(IsLeapYear(-1000));
        System.out.println(IsLeapYear(1600));
        System.out.println(IsLeapYear(2017));
        System.out.println(IsLeapYear(2000));
	}

}
