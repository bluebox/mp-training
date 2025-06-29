public class ComplexNumber {
    
    private double real;
    private double imaginary;
    public ComplexNumber(double real,double imaginary){
        this.real=real;
        this.imaginary=imaginary;
    }
    public double getImaginary() {
        return imaginary;
    }
    public double getReal() {
        return real;
    }
    public void add(double real,double imaginary){
        this.real+=real;
        this.imaginary+=imaginary;
    }
    public void add(ComplexNumber c){
        this.real+=c.real;
        this.imaginary+=c.imaginary;
    }
     public void subtract(double real,double imaginary){
        this.real-=real;
        this.imaginary-=imaginary;
    }
    public void subtract(ComplexNumber c){
        this.real-=c.real;
        this.imaginary-=c.imaginary;
    }
}
