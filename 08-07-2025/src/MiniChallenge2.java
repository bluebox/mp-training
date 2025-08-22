
public class MiniChallenge2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String arr[]= {
				"This is tarun.",
				"this is ravi",
				"Hello all"
		};
		
		String pattern="^[A-Z][a-z ]*\\.$";
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i].matches(pattern))
				System.out.println("Matched");
			else
				System.out.println("Not matched");
		}

	}

}
