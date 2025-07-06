package lambdafunctions;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Scanner;

public class minich1 {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
    Consumer<String> printwords=new Consumer<String>(){
        public void accept(String sentence){
            String[] parts=sentence.split(" ");
            for(String part:parts){
                System.out.println(part);
            }
        }
    };
    Consumer<String> printwordsl=sentence->{
        String[] parts=sentence.split(" ");
        for(String part:parts){
            System.out.println(part);
        }
    };
    System.out.println("enter string....");
    String s1=sc.nextLine();
    printwords.accept(s1);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    printwordsl.accept(s1);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    Consumer<String> printwordsforeach=sentence->{
        String[] parts=sentence.split(" ");
        Arrays.asList(parts).forEach(s-> System.out.println(s));

    };
    
    printwordsforeach.accept(s1);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    Consumer<String> printwordsconcise=sentence->{
        Arrays.asList(sentence.split(" ")).forEach(s-> System.out.println(s));
    };
    printwordsconcise.accept(s1);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");


    UnaryOperator<String> everySecondchar = source -> {
        StringBuilder returnval = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnval.append(source.charAt(i));
            }
        }
        return returnval.toString();
    };

    String result = everySecondchar.apply(s1);
    System.out.println("Every 2nd char: " + result);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    String result2 = everysecondcharcter(everySecondchar, s1);
    System.out.println("every 2nd char"+result2);
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    Supplier<String> ilovejava=()->"I Love Java!";
    Supplier<String> ilovejava2 = () -> {
            return "I Love Python";};
    System.out.println(ilovejava.get());
    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    System.out.println(ilovejava2.get());
    

}
public static String everySecondchar(String source){
    StringBuilder returnval=new StringBuilder();
    for(int i=0;i<source.length();i++){
        if(i%2==1){
            returnval.append(source.charAt(i));
        }
    }
    return returnval.toString();


}
public static String everysecondcharcter(Function<String,String> func,String source){
    return func.apply(source);
}
}