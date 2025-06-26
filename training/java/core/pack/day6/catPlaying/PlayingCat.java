package day6.catPlaying;

public class PlayingCat {
public static boolean isCatPlaying(boolean summer ,int temp) {
	boolean playing;
	if(summer) {
		if(temp>=25 && temp<=45)
			playing=true;
		else 
			playing=false;
	}
	else {
		if(temp>=25 && temp<=35)
			playing=true;
		else 
			playing =false;
	}
	return playing;
}
}
