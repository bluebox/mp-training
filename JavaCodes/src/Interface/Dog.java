package Interface;

public class Dog implements Animal{

	@Override
	public void run() {
		System.out.println(this.getClass().getSimpleName()+" running in interface");
	}

	@Override
	public void jump() {
		System.out.println(this.getClass().getSimpleName()+" jjump in interface");
	}

}
