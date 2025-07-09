import java.time.LocalTime;

public class Localtime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalTime currentTime=LocalTime.now();
		System.out.println("current time is "+currentTime);
		LocalTime t3=currentTime.plusMinutes(20);
		System.out.println("after adding minutes "+t3);
		LocalTime t4=t3.plusHours(2);
		System.out.println("after adding hours "+t4);
		LocalTime t5=t4.minusMinutes(20);
		System.out.println("after subtracting minutes "+t5);
	}

}
