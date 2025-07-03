package Day6_02_07_LambdaChallange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class Main {

    public static List<String> transformStrings(String[] inputArray, List<UnaryOperator<String>> transformations) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < transformations.size(); i++) {
            UnaryOperator<String> transformation = transformations.get(i);
            resultList.add(transformation.apply(inputArray[i]));
        }
        return resultList;
    }

    public static void main(String[] args) {
        String[] names = {"saketh", "rahul", "venu", "shiva kumar", "sai charan"};

        List<UnaryOperator<String>> operations = List.of(
            str -> str.toUpperCase(),

            str -> {
                int middle = (str.length() - 1) / 2;
                StringBuilder builder = new StringBuilder(str);
                builder.setCharAt(middle, 'C');
                return builder.toString();
            },

            str -> {
                String reversed = new StringBuilder(str).reverse().toString();
                return str + " " + reversed;
            },

            str -> str.substring(str.length() / 2),

            str -> str + " This is concatenated."
        );

        System.out.println("Original Strings: " + Arrays.toString(names));
        List<String> modified = transformStrings(names, operations);
        System.out.println("Transformed Strings: " + modified);
    }
}
