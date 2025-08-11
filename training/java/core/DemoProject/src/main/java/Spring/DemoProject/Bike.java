package Spring.DemoProject;

import org.springframework.stereotype.Component;

@Component
public class Bike implements vehicle{
	public void drive() {
		System.out.println("drivee sloww");
	}

}
