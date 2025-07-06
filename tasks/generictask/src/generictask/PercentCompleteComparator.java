package generictask;
import java.util.Comparator;

public class PercentCompleteComparator implements Comparator<LPAStudent> {
    @Override
    public int compare(LPAStudent a, LPAStudent b) {
        return Double.compare(a.getPercentComplete(), b.getPercentComplete());
    }
}
