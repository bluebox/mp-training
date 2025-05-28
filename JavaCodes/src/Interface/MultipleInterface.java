package Interface;

public class MultipleInterface implements Animal,Human{

	@Override
	public void drive() {
		System.out.println("Driving");
	}

	@Override
	public void run() {
		System.out.println("run");
	}

	@Override
	public void jump() {
	  System.out.println("jupmp");
	}

}
