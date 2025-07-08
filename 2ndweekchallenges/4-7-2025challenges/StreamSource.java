import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamSource {
    

    public static void main(String[] args) {
        
        List<Integer> number=new ArrayList<>();
        number.add(10);
        number.add(12);
        number.add(17);
        number.add(32);
        number.add(46);
        number.add(66);
        Stream<String> label=number.stream() 
                        .filter(n->(n>=1 && n<=15))
                        .map(n->"B1-B15");
                        
        Stream<String> label1=number.stream() 
                        .filter(n->(n>=16 && n<=30))
                        .map(n->"I16-I30");
        Stream<String> label2=number.stream() 
                        .filter(n->(n>=31 && n>45))
                        .map(n->"N31-N45");
        Stream<String> label3=number.stream() 
                        .filter(n->(n>=46 && n<=60))
                        .map(n->"G45-G60");
        Stream<String> label4=number.stream() 
                        .filter(n->(n>=61 && n<=75))
                        .map(n->"O61-O75");
        Stream<String> label5=Stream.concat(label, label1);
        Stream<String> label6=Stream.concat(label5, label2);
        Stream<String> label7=Stream.concat(label6, label3);
        Stream<String> label8=Stream.concat(label7, label4);
        label8.forEach(System.out::println);
        
        
        


        
    }

}
