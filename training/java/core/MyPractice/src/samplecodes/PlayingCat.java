package samplecodes;

public class PlayingCat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isCatPlaying(true,36));
		System.out.println(isCatPlaying(false,41));
	}
	public static boolean isCatPlaying(boolean summer,int temp) {
		if(summer) {
			return temp>=25 && temp<=45;
		}
		return temp>=25 && temp<=35;
	}

}
