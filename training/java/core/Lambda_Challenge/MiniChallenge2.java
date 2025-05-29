package Lambda_Challenge;


public class MiniChallenge2 {
    public static void main(String[] args) {
        String input = "abcdefg";
        StringBuilder result = new StringBuilder();
        java.util.stream.IntStream.range(0, input.length())
            .filter(i -> i % 2 == 1)
            .forEach(i -> result.append(input.charAt(i)));

        System.out.println(result.toString());
    }
}

