package SealedClassesExample;

public final class Circle extends Shape{

	@Override
	public void draw() {
		super.draw();
      System.out.println("Drawing a Circle.");
	}

}
