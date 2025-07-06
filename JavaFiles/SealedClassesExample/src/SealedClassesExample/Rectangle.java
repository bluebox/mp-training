package SealedClassesExample;

public final class Rectangle extends Shape{

	@Override
	public void draw() {
		super.draw();
        System.out.println("Drawing a Rectangle.");

	}

}
