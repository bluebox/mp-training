
public interface HourlyEmployee {
	public default double getDoublePay(double hourlyPayRate,int hours) {
		return hourlyPayRate*hours;
	}
}
