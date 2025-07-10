package July1;

public class LPAStudent extends Student{
    private double gpa;

    public LPAStudent(String id, String name, String course, double gpa) {
        super(id, name, course);
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	@Override
	public String toString() {
		return "LPAStudent [ " + super.toString() + " gpa= " + gpa  +" ]";
	}

    
}
