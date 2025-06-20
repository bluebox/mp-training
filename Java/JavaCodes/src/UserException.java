import java.util.Scanner;
// from this we can say that the finally code block run even if we have return in try and catch
class InvalidAge extends Exception{

	private static final long serialVersionUID = 1L;
	public InvalidAge(String data){
		super(data);	
	}
}
public class UserException {

	public static void main(String[] args)  {
		System.out.println("enter age");
		Scanner sc=new Scanner(System.in);
		int age=sc.nextInt();
	    try {
	    	if(age<0 || age>100) {
	    		throw new InvalidAge("enter valid age");
	    	}
	    	return ;
	    }
	    catch(InvalidAge e) {
	    	System.out.println(e.getMessage());
	    	return ;
	    }
	    finally {
	    	System.out.println("jai shree ram");
	    }	
	}

}
