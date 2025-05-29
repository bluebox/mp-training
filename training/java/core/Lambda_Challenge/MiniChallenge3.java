package Lambda_Challenge;
// import java.util.stream;

public class MiniChallenge3 {
    public static void everySecondChar(int num){
        StringBuilder s = new StringBuilder();
        String st = String.valueOf(num);

        java.util.stream.IntStream.range(0 , st.length())
        .filter(i -> i%2==1)
        .forEach(i -> s.append(st.charAt(i)));

        System.out.println(String.valueOf(s));
    }
    public static void main(String[] args) {
        everySecondChar(1234567890);
    }
}
