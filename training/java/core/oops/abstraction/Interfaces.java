package oops.abstraction;

interface Drawable {
    void draw();
}

class Circle implements Drawable {
    public void draw() {
        System.out.println("Drawing circle");
    }
}

public class Interfaces {
    public static void main(String[] args) {
        Drawable d = new Circle();
        d.draw();
    }
}

