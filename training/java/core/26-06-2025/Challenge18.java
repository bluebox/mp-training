
public class Challenge18 {
	public static boolean canPack(int bigCount,int smallCount,int goal)
	{
		int ans=(bigCount*5)+smallCount;
		if(ans>=goal)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(canPack(1,0,5));
		

	}

}
