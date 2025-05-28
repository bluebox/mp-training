package Cat;

public class IsCatPlaying {

	public static void main(String[] args) {
		
		boolean isPlaying = isCatPlaying(true,35);
		
		System.out.println(isPlaying ? ("cat is playing"):("cat is not playing"));
	}
	public static boolean isCatPlaying(boolean isSummer,int temperature)
	{
		boolean isPlaying = true;
		if(isSummer)
		{
			if(temperature < 25 && temperature >45)
			{
				isPlaying = false;
			}
		}
		else if(temperature < 25 && temperature >35)
		{
			isPlaying = false;
		}
		
		return isPlaying;
	}

}
