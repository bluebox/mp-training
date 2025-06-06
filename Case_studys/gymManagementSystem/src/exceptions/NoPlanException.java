package exceptions;

public class NoPlanException extends Exception{
	
	public void msg() {
		System.out.println("Currently No Active Plan !!!");
	}
}
