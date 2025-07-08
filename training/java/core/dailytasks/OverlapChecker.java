public class OverlapChecker {
    public static void main(String[] args) {
        int[][] intervals = {
            {1, 8},
            {5,6},
            {20, 24},
            {23,30},
            {27,32}
        };

        for (int i = 0; i < intervals.length; i++) {
            int st1 = intervals[i][0];
            int ed1 = intervals[i][1];

            boolean hasOverlap = false;
            StringBuilder overlappingWith = new StringBuilder();

            
            for (int j = i - 1; j >= 0; j--) {
                int st2 = intervals[j][0];
                int ed2 = intervals[j][1];

                
                if (!(ed1 < st2 || ed2 < st1)) {
                    hasOverlap = true;
                    overlappingWith.append("[").append(st2).append(",").append(ed2).append("] ");
                }
            }

            System.out.print(" [" + st1 + "," + ed1 + "]");
            if(hasOverlap) {
                System.out.println("False : " + overlappingWith);
            } else {
                System.out.println("True");
            }
        }
    }
}