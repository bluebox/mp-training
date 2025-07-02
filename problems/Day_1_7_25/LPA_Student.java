package Day_1_7_25;

public class LPA_Student  extends Student{
	    private double gpa;

	    public LPA_Student(String id, String name, String course, double gpa) {
	        super(id, name, course);
	        this.gpa = gpa;
	    }

	    public double getGpa() {
	        return gpa;
	    }

	    @Override
	    public String toString() {
	        return super.toString() + " (GPA: " + gpa + ")";
	    }
	}


