package corejava.june27_Constructors;

public class ComplexNumber {
	private double real;
	private double img;
	
	public ComplexNumber(double real, double img) {
		super();
		this.real = real;
		this.img = img;
	}

	public double getReal() {
		return real;
	}

	public double getImg() {
		return img;
	}
	
	public String add(double real,double img) {
		this.real+=real;
		this.img+=img;
		return this.real+"+ "+this.img+"i";
	}
	
	public String add(ComplexNumber c) {
		this.real+=c.real;
		this.img+=c.img;
		return this.real+"+ "+this.img+"i";
	}
	
	public String subtract(double real,double img) {
		this.real-=real;
		this.img-=img;
		return this.real+"+ "+this.img+"i";
	}
	
	public String subtract(ComplexNumber c) {
		this.real-=c.real;
		this.img-=c.img;
		return this.real+"+ "+this.img+"i";
	}
}
