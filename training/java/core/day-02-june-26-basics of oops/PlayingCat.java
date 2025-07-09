package day2;

public class PlayingCat {

	public static void main(String[] args) {
		System.out.println(isCatPlaying(true,10));
		System.out.println(isCatPlaying(false,36));
		System.out.println(isCatPlaying(false,35));
	}
	public static boolean isCatPlaying(boolean isSummer,int temperature) {
		if (isSummer) {
			return (25<=temperature && temperature<=45);
		}
		else {
			return (25<=temperature && temperature<=35);
		}
	}
}
