package july17th.InterfacePractice;

public class Ex3 implements Ex1 {

	@Override
	public void copy() {
		System.out.println("this is ex2 copy");
	}

	@Override
	public void paste() {
		System.out.println("this is ex2 paste");
		
	}

	@Override
	public void delete() {
		System.out.println("this is ex2 delete");
		
	}

	@Override
	public void shift() {
		System.out.println("this is ex2 shift");
	}

	

}
