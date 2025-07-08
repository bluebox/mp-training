
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
public class Challenge{
    public static void main(String[] args) {
        String sentence="Hello,guru";
        
        Consumer<String> printTheParts=s->{
            String[] parts=sentence.split(",");
            for(String part:parts){
                System.out.println(part);
            }
           
        };
         printTheParts.accept(sentence);

         Function<String,String> everySecondChar=(s)->{
            StringBuilder returnVal=new StringBuilder();
            for(int i=0;i<s.length();i++){
                if(i%2==1)returnVal.append(s.charAt(i));
            }
            System.out.println(returnVal.toString());
            return returnVal.toString();
         };
         String movie=everySecondChar.apply("this is good movie");
         String num=everySecondChar.apply("1234567890");
         System.out.println(movie+num);
        Supplier<String> exp=()->"I love Java";
        String iLoveJava=exp.get();
        System.out.println(iLoveJava);


        String supplierResult=exp.get();









        


         
        
        
    }
    public void func(Function<String,String> f,String str){
        f.apply(str);
    }
    public void func2(Supplier<String> s,String str){
        s.get();
    }
}