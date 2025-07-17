package july_1;

import java.util.*;

public class FinalChallenge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QueryList<LPAStudent> students = new QueryList<>();

        Random rand = new Random();
        String[] names = {"Ava", "Ben", "Cara", "Dan", "Ella", "Finn", "Gina", "Hugo", "Ivy", "Jack"};
        String[] courses = {"Java", "Python", "AI", "Cloud", "Web"};

        for (int i = 1; i <= 25; i++) {
            String name = names[rand.nextInt(names.length)];
            String course = courses[rand.nextInt(courses.length)];
            int year = rand.nextInt(4) + 1;
            int id = i;
            double percent = rand.nextInt(101);
            students.add(new LPAStudent(name, course, year, id, percent));
        }

        System.out.println("=== Students with <= 50% Completion ===");
        List<LPAStudent> filtered = students.getMatches("percentcomplete", "50");

        // Sort by natural order (studentId)
        filtered.sort(Comparator.naturalOrder());
        System.out.println("\n--- Sorted by Student ID ---");
        filtered.forEach(System.out::println);

        // Sort by course then percentComplete
        filtered.sort(Comparator
                .comparing(LPAStudent::getCourse)
                .thenComparing(LPAStudent::getPercentComplete));
        System.out.println("\n--- Sorted by Course then Percent Complete ---");
        filtered.forEach(System.out::println);

        // Meal example
        System.out.println("\n=== Meal Example ===");
        Meal meal = new Meal("Veg Burger", 99);
        meal.addToppings("Cheese", "Tomato", "Bacon");
        meal.printMeal();
    }
}
