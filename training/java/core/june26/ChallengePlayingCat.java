package com.tulasidhar.june26;

public class ChallengePlayingCat {

	public static void main(String[] args) {
		System.out.println("isCatPlaying(true,10) : " + isCatPlaying(true,10));
		System.out.println("isCatPlaying(false,36) : " + isCatPlaying(false,36));
		System.out.println("isCatPlaying(true,10) : " + isCatPlaying(false,35));

	}
	
	//temperature explicitly mentioned as 'int' in problem
	public static boolean isCatPlaying(boolean isSummer , int temperature){
		if(isSummer) {
			if(temperature <= 45 && temperature >=25) {
				return true;
			}
			else {
				return false;
			}
		}
		
		if(temperature <= 35 && temperature >=25) {
			return true;
		}
		else {
			return false;
		}
		
	}
}


