import java.util.*;

public class ArrayListChallenge {

    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the choice");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter the element to add in the ArrayList");
                    String m = sc.nextLine();
                    if (!al.contains(m)) {
                        al.add(m);
                        System.out.println("Element added.");
                    } else {
                        System.out.println("Element already exists.");
                    }
                    al.sort(Comparator.naturalOrder());
                    System.out.println(al.toString());
                    break;

                case 2:
                    System.out.println("Enter the element to remove from the ArrayList");
                    String m1 = sc.nextLine();
                    if (al.remove(m1)) {
                        System.out.println("Element removed.");
                    } else {
                        System.out.println("Element not found.");
                    }
                    al.sort(Comparator.naturalOrder());
                    System.out.println(al.toString());
                    break;

                case 0:
                    System.out.println("Exiting");
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}

