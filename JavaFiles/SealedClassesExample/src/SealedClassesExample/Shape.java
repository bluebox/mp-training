package SealedClassesExample;

public sealed class Shape permits Circle, Rectangle {
    public void draw() {
        System.out.println("Drawing a shape.");
    }
}
