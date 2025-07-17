import java.util.*;
import java.util.regex.Pattern;

public class romantoint {
    public static int ri(String s1) {
        String s=s1.toUpperCase();
        Map<Character, Integer> dict1 = new HashMap<>();
        dict1.put('I', 1);
        dict1.put('V', 5);
        dict1.put('X', 10);
        dict1.put('L', 50);
        dict1.put('C', 100);
        dict1.put('D', 500);
        dict1.put('M', 1000);
        for (char c : s.toCharArray()) {
            if (!dict1.containsKey(c)) {
                return -1;
            }
        }

        int total = 0;
        int prev = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int val = dict1.get(s.charAt(i));

            if (val < prev) {
                total -= val;
            } else {
                total += val;
            }

            prev = val;
        }

        return total;
    }
    public static boolean isvalid(String s1){
        String s=s1.toUpperCase();

        String regex = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        return Pattern.matches(regex, s);
    }

    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        System.out.println("enter roman no:");
        String s=sc.next();
        if(isvalid(s)){
        if (ri(s)>0){
                    System.out.println(ri(s));      

        }
    }
        else{
        System.out.println("invalid roman no");
    }
        
    }
}
