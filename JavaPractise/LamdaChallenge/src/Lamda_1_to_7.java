import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
public class Lamda_1_to_7 {
    public static void main(String[] args){
        Consumer<String> printTheParts = sentence ->{
            String[] parts=sentence.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
        System.out.println("Mini Challenge 1:");
        printTheParts.accept("Hello from Java lamda");
        Function<String,String> everySecondChar = source ->{
            StringBuilder str=new StringBuilder();
            for(int i=0;i<source.length();i++) {
                if(i % 2 == 1){
                    str.append(source.charAt(i));
                }
            }
            return str.toString();
        };
        System.out.println("Mini Challenge 2:");
        System.out.println(everySecondChar.apply("Akash"));
        String result =everySecondChar.apply("1234567890");
        System.out.println("Mini Challenge 3 Result: " + result);
        String finalResult =everySecondCharacter(everySecondChar,"1234567890");
        System.out.println("Mini Challenge 5 Result: "+finalResult);
        Supplier<String> iLoveJava = () -> "I love Java";
        String supplierResult = iLoveJava.get();
        System.out.println("Mini Challenge 7 Result: "+supplierResult);
    }
    public static String everySecondCharacter(Function<String, String> func, String value) {
        return func.apply(value);
    }
}