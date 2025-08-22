
public class FlourPackRequirement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(canPack(1,0,4));
	}
	public static boolean canPack(int bigCount,int smallCount,int goal)
	{
		  if (bigCount<0||smallCount<0||goal<0) {
	            return false;
	        }
	        int m1 = goal / 5;
	        int m2= Math.min(m1, bigCount);
	        int remaining = goal - (m2 * 5);
	        return smallCount >= remaining;
	    }	
	}

