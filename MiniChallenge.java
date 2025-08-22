
public class MiniChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double amount=100,interest=0;
		
          for(double i= 7.5 ; i<=10 ; i=i+0.25) {
        	  interest = amount * i;
        	  System.out.println("Interest for "+amount+ " with interest "+ i +"is"+interest );
          }
	}

}
