package fundamentals;
class Duration
{
	public void findDuration(int seconds)
	{
		int hours=seconds/(60*60);
		int remSec=seconds%(60*60);
		int mins=remSec/60;
		remSec=remSec%60;
		System.out.println(hours+"h "+mins+"m "+remSec+"s");
	}
	public void findDuration(int mins2,int seconds)
	{
		seconds+=seconds+mins2*60;
		int hours=seconds/(60*60);
		int remSec=seconds%(60*60);
		int mins=remSec/60;
		remSec=remSec%60;
		System.out.println(hours+"h "+mins+"m "+remSec+"s");
	}
}
public class MethodOverLoading2 {
	public static void main(String args[])
	{
		Duration duration=new Duration();
		duration.findDuration(2000);
		duration.findDuration(100,40);
	}
}
