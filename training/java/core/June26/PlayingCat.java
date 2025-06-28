package June26;

public class PlayingCat {
	public static void main(String[] args) {
		System.out.println("Cat is " + ((isCatPlaying(true, 10))? "":"not ") + "playing");
		System.out.println("Cat is " + ((isCatPlaying(false, 36))? "":"not ") + "playing");
		System.out.println("Cat is " + ((isCatPlaying(false, 35))? "":"not ") + "playing");
	}
	public static boolean isCatPlaying (boolean isSummer, int temperature) {
		if(isSummer) {
			if(temperature >= 25 && temperature <= 45) return true;
		}else {
			if(temperature >= 25 && temperature <= 35) return true;
		}
		return false;
	}
}
