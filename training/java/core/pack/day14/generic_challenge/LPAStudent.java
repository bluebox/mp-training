package day14.generic_challenge;

import java.util.Random;

public class LPAStudent extends Student {

    private double percentComplete;

    public LPAStudent() {
    	Random random=new Random();
        percentComplete = random.nextDouble(0, 100.001);
    }

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete);
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {

        if (fieldName.equalsIgnoreCase("percentComplete")) {
            return percentComplete <= Integer.parseInt(value);
        }

        return super.matchFieldValue(fieldName, value);
    }
}