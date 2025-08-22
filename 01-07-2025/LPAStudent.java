package com.prana;

public class LPAStudent extends Student {
    private double percentComplete;

    public LPAStudent(String name, String course, int yearStarted, String studentId, double percentComplete) {
        super(name, course, yearStarted, studentId);
        this.percentComplete = percentComplete;
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        if ("PERCENTCOMPLETE".equalsIgnoreCase(fieldName)) {
            try {
                double val = Double.parseDouble(value);
                return this.percentComplete <= val;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return super.matchFieldValue(fieldName, value);
    }

    @Override
    public String toString() {
        return super.toString() + " %Complete: " + percentComplete;
    }
}
