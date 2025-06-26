package junitprograms;

public class StringUtil {


 public static String removeDuplicates(String input) {
     if (input == null) return null;

     StringBuilder result = new StringBuilder();
     boolean[] seen = new boolean[256];

     for (char c : input.toCharArray()) {
         if (!seen[c]) {
             seen[c] = true;
             result.append(c);
         }
     }

     return result.toString();
 }
}

