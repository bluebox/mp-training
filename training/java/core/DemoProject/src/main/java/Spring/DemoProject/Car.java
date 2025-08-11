package Spring.DemoProject;

import org.springframework.stereotype.Component;

@Component
public class Car implements vehicle {
	private Tyre tyre;
	public Tyre getTyre() {
		return tyre;
	}
	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}
	public void drive()
	{
		System.out.println("goo fast");
	}

}
