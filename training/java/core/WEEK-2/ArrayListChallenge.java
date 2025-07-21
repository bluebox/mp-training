import java.util.Scanner;
import java.util.ArrayList;

public class ArrayListChallenge {
    public static ArrayList<Integer> readIntegers(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the elements seperated by commas : ");
        String input = sc.nextLine();
        String[] stringArray = input.split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for(String str : stringArray) {
            try {
                list.add(Integer.parseInt(str.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + str + ". Please enter integers only.");
            }
        }
        return list;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("0. Exit System\n1. Add Element\n2. Remove Element\n3. Display Elements");
        while (true) {
            System.out.println("Enter your choice: ");
            String stringInput=sc.nextLine();
            try{
                int choice=Integer.parseInt(stringInput);
                switch (choice) {
                    case 0:
                        System.out.println("Exiting the system.");
                        sc.close();
                        return;
                    case 1:
                        System.out.print("Enter Elements to be added as comma separated values: ");
                        ArrayList<Integer> newElements = readIntegers();
                        list.addAll(newElements);
                        System.out.println("Elements added: " + newElements);
                        break;
                    case 2:
                        if (list.isEmpty()) {
                            System.out.println("List is empty. Nothing to remove.");
                        } else {
                            System.out.print("Enter elements to be removed as comma separated values: ");
                            ArrayList<Integer> elementsToRemove = readIntegers();
                            list.removeAll(elementsToRemove);
                            System.out.println("Elements removed: " + elementsToRemove);
                        }
                        break;
                    case 3:
                        System.out.println("Current elements in the list: " + list);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                continue;
            }
        }
    }
}
