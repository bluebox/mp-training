import java.util.Scanner;

public class HrMinDays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Seconds: ");
		int a=sc.nextInt();
		
		Time(a);
	}
	
	public static void Time(int sec) {
		int min=sec/60;
		int Rsec=sec%60;
		int Hr=min/60;
		int Rmin=min%60;
		
		int days=Hr/24;
		int RHr=Hr%24;
		
		int Months=days/30;
		int RDays= days%24;
		
		int RMonths=Months%30;
		int Year=Months/12;
		
		
		
		
		System.out.println("The Given Time is :"+Year+"Y :"+RMonths+"M :"+RDays+"D :"+RHr+"hr :"+Rmin+"Min :"+Rsec+"Sec");
	}

}
