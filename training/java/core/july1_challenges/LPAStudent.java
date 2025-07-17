package july_1;

public class LPAStudent extends Student {
    private double percentComplete;

    public LPAStudent(String name, String course, int year, int studentId, double percentComplete) {
        super(name, course, year, studentId);
        this.percentComplete = percentComplete;
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        if (fieldName.equalsIgnoreCase("percentcomplete")) {
            return percentComplete <= Double.parseDouble(value);
        }
        return super.matchFieldValue(fieldName, value);
    }

    @Override
    public String toString() {
        return super.toString() + " - " + percentComplete + "% complete";
    }
}
