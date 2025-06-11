package day14.generic_challenge;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        QueryList<LPAStudent> queryList = new QueryList<>();
        for (int i = 0; i < 25; i++) {
            queryList.add(new LPAStudent());
        }

        System.out.println("Ordered");
        queryList.sort(Comparator.naturalOrder());
        printList(queryList);

        System.out.println("Matches");
        var matches = queryList
                .getMatches("PercentComplete", "50")
                .getMatches("Course", "Python");

        matches.sort(new LPAStudentComparator());
        printList(matches);

        System.out.println("Ordered");
        matches.sort(null);
        printList(matches);

    }

    public static void printList(List<?> students) {

        for (var student : students) {
            System.out.println(student);
        }
    }
}