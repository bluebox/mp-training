package com.prana;
import java.util.Comparator;

public class PercentCompleteComparator implements Comparator<LPAStudent> {
    @Override
    public int compare(LPAStudent s1, LPAStudent s2) {
        return Double.compare(s1.getPercentComplete(), s2.getPercentComplete());
    }
}