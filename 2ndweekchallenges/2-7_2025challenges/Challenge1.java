import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.*;
public class Challenge1 {
    public static void main(String[] args) {
        Random random=new Random();
        String sentence="ravi,Raju,kiRan,kUmar";
        UnaryOperator<String> uppername=(String s)->s.toUpperCase();
        UnaryOperator<String> middleinit=(String s)->{
           
            char value=(char)(64+random.nextInt(26));
            s=s+""+value;
            return s;

        };
        UnaryOperator<String> lastname=(String s)->{StringBuilder str=new StringBuilder(s);
        String old_str=s;
    String new_str=old_str+str.reverse();
return new_str;};
            String[] parts=sentence.split(",");
             BiFunction<String,String,String> last_name=String::concat;
            for(String part:parts){
                String s1=method(uppername, part);
                String s2=method(middleinit, part);
                String s3=method(lastname, part);
                String s4="";
                System.out.println(method1(last_name,s1,s2,s4));
                System.out.println(method1(last_name,s2,s3,s4));

            
            }
           

        

    }
    public static String method(UnaryOperator<String> u,String s){
        
        return u.apply(s);
     }
     public static String method1(BiFunction<String,String,String> b,String s1,String s2,String s3){
        s3=b.apply(s1, s2);
        return s3;
     }
    
}
