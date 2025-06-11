import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class ArratListChallenge1{
    public static void main(String[] args){
         Scanner scanner = new Scanner(System.in);
        ArrayList<String> grocery=new ArrayList<>();
        boolean t=true;
        while(t){
            System.out.println("""
                    Available  actions:
                    0 - ShutDown
                    1 - to add item(s) to list (comma delimited list)
                    2 -to remove any item(s) (comma delimited list)
                    """);
            int a=scanner.nextInt();
            switch(a){
                case 1 :addItems(grocery);break;
                case 2 :removeItems(grocery);break;
                case 0 :t=false;break;
                default :System.out.println("Invalid input.Try again !!!");
            }
        System.out.println("the ArrayList is:");
        System.out.println(grocery.toString());
        }

    }
    public static void addItems(ArrayList<String> grocery){

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the items sapareted by comma:");
        String items= scanner.nextLine();
        ArrayList<String> itemsToAdd=new ArrayList<>(List.of(items.split(",")));
        grocery.addAll(itemsToAdd);

    }
    public static void removeItems(ArrayList<String> grocery){

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the items sapareted by comma:");
        String items= scanner.nextLine();
        ArrayList<String> itemsToRemove=new ArrayList<>(List.of(items.split(",")));
        grocery.removeAll(itemsToRemove);

    }
}