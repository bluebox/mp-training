
public class MiniChallenge3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String arr[]= {
				"The bike is red,and has flat tires.",
				"I love being a new L.P.A student",
				"Hello,friends and family:Welcome",
				"How are you,Mary?"
		};
		String pattern="^[A-Z][\\W\\S,.'\":;-]*[.!?]$";
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i].matches(pattern))
				System.out.println("Matched");
			else
				System.out.println("Not Matched");
		}

	}

}
