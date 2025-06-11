public class MinutesToYearsAndDays {

	public static void main(String[] args) {
printYearsAndDays(525600);
printYearsAndDays(1051200);
printYearsAndDays(561600);

	}
	public static void printYearsAndDays(long minutes) {
		if (minutes<0){System.out.println("invalid input");}
		System.out.println(minutes+" min = "+minutes/525600+" y and "+((minutes%525600)/(24*60))+" d");
	}

}
