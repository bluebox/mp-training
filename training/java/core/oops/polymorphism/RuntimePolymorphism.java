class Animall {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dogg extends Animall {
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animall {
    void sound() {
        System.out.println("Cat meows");
    }
}

public class RuntimePolymorphism {
    public static void main(String[] args) {
        Animall a;

        a = new Animall();
        a.sound();

        a = new Dogg();
        a.sound();

        a = new Cat();
        a.sound();
    }
}
