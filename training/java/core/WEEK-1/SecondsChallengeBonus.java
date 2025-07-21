public class SecondsChallengeBonus {

	public static void main(String[] args) {
		System.out.println(getDurationString(3945));
		System.out.println(getDurationString(78,254));
	}
	public static String getDurationString(int seconds) {
		int minutes=seconds/60;
		seconds%=60;
		return getDurationString(minutes,seconds);
	}
	public static String getDurationString(int minutes, int seconds) {
		minutes+=seconds/60;
		int hours=minutes/60;
		int remainingMinutes=minutes%60;
		int remainingSeconds=seconds%60;
		return hours+" hours "+remainingMinutes +" minutes "
				+ remainingSeconds +" seconds";
	}
}