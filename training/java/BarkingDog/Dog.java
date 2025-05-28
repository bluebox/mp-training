package BarkingDog;

public class Dog {

	public static void main(String[] args) {
		
		System.out.println("you should "+ (shouldWakeUp(true,24)?"wake up":"sleep" ));

	}
	public static boolean shouldWakeUp(boolean barking,int hourOfDay)
	{
		boolean res=true;
		hourOfDay%=24;
		if(barking && hourOfDay >= 8 && hourOfDay <= 22)
			res=false;
		else if(!barking)
			res=false;
		return res;
		
	}

}
