package Day3;

public class complexNumber {
   private double real;
   private double imaginary;
  
    public complexNumber(double real,double imaginary) {
    	this.real=real;
    	this.imaginary=imaginary;
    }

	public double getReal() {
		return real;
	}

	public double getImaginary() {
		return imaginary;
	}
    
    public void add(double real,double imaginary) {
    	this.real=this.real+real;
    	this.imaginary=this.imaginary+imaginary;
    	System.out.println("after the operation the real value is "+this.real+"the imaginary value is "+this.imaginary);
    }
    
    public void add(complexNumber number) {
    	this.real=this.real+number.real;
    	this.imaginary=this.imaginary+number.imaginary;
    	System.out.println("after the operation the real value is "+this.real+"the imaginary value is "+this.imaginary);
    }
    
    public void subtract(double real,double imaginary) {
    	this.real=this.real-real;
    	this.imaginary=this.imaginary-imaginary;
    	System.out.println("after the operation the real value is "+this.real+"the imaginary value is "+this.imaginary);
    }
    
    public void subtract(complexNumber number) {
    	this.real=this.real-number.real;
    	this.imaginary=this.imaginary-number.imaginary;
    	System.out.println("after the operation the real value is "+this.real+"the imaginary value is "+this.imaginary);
    }
}
