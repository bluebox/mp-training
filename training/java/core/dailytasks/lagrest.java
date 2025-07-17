import java.util.Arrays;
import java.util.Comparator; 
import java.util.Scanner; 

class LargestNumber { 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt(); 

        int[] nums = new int[n]; 
        System.out.println("Enter elements non--ve integers:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt(); 
        }

        // scanner.close(); 

        String[] elements = new String[n];
        for (int i = 0; i < n; i++) {
            elements[i] = Integer.toString(nums[i]); 
        }
         Arrays.sort(elements, new Comparator<String>() { 
        public int compare(String a, String b) {
                String first = a + b;
                String second = b + a;
                
                return second.compareTo(first);
            }
        });

        if (elements.length > 0 && elements[0].equals("0")) {
            System.out.println("0");
            return;
        }

        StringBuilder result = new StringBuilder(); 
        for (String val : elements) {
            result.append(val);
        }

        System.out.println(result.toString());
    }
}

        
        



    
