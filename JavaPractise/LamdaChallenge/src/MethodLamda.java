import java.util.*;
public class MethodLamda {
    public static void main(String[] args){
        String[] names={"Shiv","Krishna","Vishnu","Brahma"};
        Random random=new Random();
        for (int i=0;i<names.length;i++){
            String name=names[i];
            name=name.toUpperCase();
            char middle=(char)('A'+random.nextInt(26));
            name=name+" "+middle+" ";
            String first=name.split(" ")[0];
            String reverse=new StringBuilder(first).reverse().toString();
            name=name+" "+reverse;
            if(first.toLowerCase().endsWith("a")|| first.toLowerCase().endsWith("e")){
                name="Ms. "+name;
            } 
            else
            {
                name="Mr."+name;
            }
            names[i] = name;
        }
        System.out.println("Transformed Names:");
        for(String n : names){
            System.out.println(n);
        }
    }
}
