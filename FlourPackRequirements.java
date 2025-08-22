
public class FlourPackRequirements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(canPack(1,0,4)) {
			System.out.println("We can Pack");
		}
		else
		{
			System.out.println("We cannot Pack");
		}
		
		if(canPack(1,0,5)) {
			System.out.println("We can Pack");
		}
		else
		{
			System.out.println("We cannot Pack");
		}
		
		if(canPack(0,5,4)) {
			System.out.println("We can Pack");
		}
		else
		{
			System.out.println("We cannot Pack");
		}
		
		if(canPack(2,2,11)) {
			System.out.println("We can Pack");
		}
		else
		{
			System.out.println("We cannot Pack");
		}
		
		if(canPack(-3,2,12)) {
			System.out.println("We can Pack");
		}
		else
		{
			System.out.println("We cannot Pack");
		}

	}
	
	public static boolean canPack(int bigCount, int smallCount, int goal) {
		int b=5;
		int s=1;
		if(bigCount < 0){
			return false;
		}
		else if(((bigCount * b) + (smallCount * s) <= goal) || (bigCount * b) <= goal) {
			return true;
		}
		else {
			return false;
		}
	}

}
