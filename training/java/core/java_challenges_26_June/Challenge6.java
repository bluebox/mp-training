
public class Challenge6 {

	public static boolean isCatPlaying(boolean summer, int temperature)
	{
		if(summer && (temperature>=25 && temperature<=35))
			return true;
		else if(summer==false && (temperature >= 25 && temperature <=45))
			return true;
		else
			return false;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isCatPlaying(true,12));
		System.out.println(isCatPlaying(false,45));
		System.out.println(isCatPlaying(true,26));
		System.out.println(isCatPlaying(false,50));
		

	}

}
