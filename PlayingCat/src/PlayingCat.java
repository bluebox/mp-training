
public class PlayingCat {

	public static boolean IsCatPlaying(boolean summer,int temp) {
		int upperlimit=summer ? 45:35;
		return temp>=25 && temp<=upperlimit;
		
	}

}
