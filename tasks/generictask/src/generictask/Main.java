package generictask;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        QueryList<LPAStudent> students = new QueryList<>();
        Scanner scanner = new Scanner(System.in);

        Random random = new Random();
        for (int i = 1; i <= 25; i++) {
            students.add(new LPAStudent(
                "Student" + i,
                "Course" + (i % 3 + 1),
                1 + random.nextInt(4),
                1000 + i,
                random.nextInt(101)
            ));
        }

        System.out.print("Enter maximum percent complete to filter (0–100): ");
        String inputPercent = scanner.nextLine();

        List<LPAStudent> filtered = students.getMatches("percentcomplete", inputPercent);

        System.out.println("\n=== Filtered Students (<= " + inputPercent + "% complete) ===");
        for (LPAStudent s : filtered) {
            System.out.println(s);
        }

        filtered.sort(Comparator.naturalOrder());
        System.out.println("\n--- Sorted by student ID (natural order) ---");
        for (LPAStudent s : filtered) {
            System.out.println(s);
        }

        filtered.sort(new PercentCompleteComparator());
        System.out.println("\n--- Sorted by percent complete ---");
        for (LPAStudent s : filtered) {
            System.out.println(s);
        }

        scanner.close();
    }
}
