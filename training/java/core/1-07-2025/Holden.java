public class Holden extends Car {
    
    public Holden(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override 
    public String startEngine() {
        return "Holden -> startEngine()";
    }

    @Override
    public String accelerate() {
        return "holden -> accelerate()";
    }

    @Override
    public String brake() {
        return "Holden -> brake()";
    }
}


