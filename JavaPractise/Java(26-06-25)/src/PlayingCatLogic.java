
public class PlayingCatLogic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(IsCatPlaying(false,35));

	}
	public static boolean IsCatPlaying(boolean summer,int temperature)
	{
		if(summer==true&&temperature>=25&&temperature<=45)
		{
			return true;
		}
		else if(summer==false&&temperature>=25&&temperature<=35)
		{
			return true;
		}
		else 
			return false;
	}

}

