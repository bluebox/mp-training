
public class ComplexNumber {
	 private double real;
	    private double imagi;

	    public ComplexNumber(double real, double imagi) {
	        this.real = real;
	        this.imagi = imagi;
	    }

	    public double getReal() {
	        return real;
	    }

	    public double getImagi() {
	        return imagi;
	    }
	    public void add(ComplexNumber number) {
	    	this.real=this.real+number.getReal();
	    	this.imagi=this.imagi+number.getImagi();
	    }
	    public void add(double real,double imagi) {
	    	this.real+=real;
	    	this.imagi+=imagi;
	    }
	    
}
