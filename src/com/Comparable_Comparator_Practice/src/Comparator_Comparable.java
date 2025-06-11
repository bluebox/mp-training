import java.util.*;
import java.util.stream.Collectors;

public class Comparator_Comparable {

    public static void main(String[] args) {

        List<Student> listOfStudents = new ArrayList<>();
        listOfStudents.add(new Student("Mourya", 172, 98));
        listOfStudents.add(new Student("Yaswini", 168, 100));
        listOfStudents.add(new Student("Suhel", 150, 89));
        listOfStudents.add(new Student("Namratha", 165, 98));
        listOfStudents.add(new Student("Mounika", 172, 98));

        // Sort using Comparable - only sorting based on one field, i.e., on height as
        //        specified in Student class
        System.out.println("Sort by Comparable's compareTo method");
        List<Student> copyList = new ArrayList<>(listOfStudents);
        copyList.sort(Student::compareTo);
        System.out.println(copyList);


        System.out.println("Sort by Comparator's naturalOrder method");
        List<Student> copyList2 = new ArrayList<>(listOfStudents);
        // This only works when your Student class, i.e., type of listOfStudents
        // implements the Comparable interface and the sort is based on the compareTo logic defined.
        copyList2.sort(Comparator.naturalOrder()); // At last, .reversed() not applicable
        System.out.println(copyList2); // copyList == copyList2, since both are based on Comparable internally.


        System.out.println("Sort by Comparator's comparing method on marks");
        List<Student> copyList3 = new ArrayList<>(listOfStudents);
        copyList3.sort(Comparator.comparing(Student::getMarks));
        System.out.println(copyList3);


        System.out.println("Sort by Comparator's comparing method on Name");
        List<Student> copyList4 = new ArrayList<>(listOfStudents);
        copyList4.sort(Comparator.comparing(Student::getName));
        System.out.println(copyList4);


        System.out.println("Sort by Comparator's comparing method on Height");
        List<Student> copyList5 = new ArrayList<>(listOfStudents);
        copyList5.sort(Comparator.comparing(Student::getHeight));
        System.out.println(copyList5);


        System.out.println("Sort using Collections");
        List<Student> copyList6 = new ArrayList<>(listOfStudents);
        Collections.sort(copyList6, (s1,s2) -> s1.getMarks() - s2.getMarks());
        System.out.println(copyList6);


        System.out.println("Sort using Collections and chaining comparing for further sorting" +
                " when sort by a field conflicts due to same value.");
        List<Student> copyList7 = new ArrayList<>(listOfStudents);
        copyList7.sort(Comparator.comparing(Student::getMarks).thenComparing(Student::getHeight)
                .thenComparing(Student::getName));
        System.out.println(copyList7);
    }
}

