package com;

import java.util.Scanner;
import java.util.*;

public class ArrayListChallenge {
    public static void main(String[] args) {
        System.out.println("""
                Available actions:
                0 - to shutdown
                1 - to add an item(s) to the list (comma delimited list)
                2 - to remove an item(s) from the list (comma delimited list)
                """);

        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        do{
            int input = scanner.nextInt();
            switch(input) {
                case 0 -> {
                    System.out.println("Shutting down...");
                    break;
                }
                case 1 -> {
                    System.out.println("Where do you want to add the item(s)? At a specific index or at the end of the list? (type 'index' or 'end')");
                    String position = scanner.next();
                    if(position.equalsIgnoreCase("index")) {
                        System.out.println("At what index do you want to insert the element");
                        int index = scanner.nextInt();
                        System.out.println("Enter the item(s) to add:");
                        String items = scanner.next();
                        String[] itemArray = items.split(",");
                        for(String item : itemArray) {
                            try {
                                int num = Integer.parseInt(item.trim());
                                list.add(index, num);
                                index++;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format: " + item);
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Where do you want to remove the item(s)? From a specific index or from the end of the list? (type 'index' or 'end')");
                    String position = scanner.next();
                    if(position.equalsIgnoreCase("index")) {
                        System.out.println("At what index do you want to remove the element");
                        int index = scanner.nextInt();
                        System.out.println("Enter the item(s) to remove:");
                        String items = scanner.next();
                        String[] itemArray = items.split(",");
                        for(String item : itemArray) {
                            try {
                                int num = Integer.parseInt(item.trim());
                                if(index < list.size() && list.get(index).equals(num)) {
                                    list.remove(index);
                                } else {
                                    System.out.println("Item not found at index " + index);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number format: " + item);
                            }
                        }
                    }
                }
            }
        }while(true);

        // scanner.close();
    }
}
