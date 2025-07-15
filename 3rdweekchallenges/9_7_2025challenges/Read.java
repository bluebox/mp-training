import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.*;
public class Read {
    public static void main(String[] args) throws IOException{
        String filepath="C://Users//Santhosh//Desktop//internship//3rdweekchallenges//9_7_2025challenges//text.txt";

        BufferedReader br = new BufferedReader(new FileReader(filepath));
        Map<String,Integer> map=new TreeMap<>();
        String line;
       while((line=br.readLine())!=null){
            System.out.println("hi hello");
            System.out.println("line"+line);
            String arr[]=line.replaceAll("\\p{P}","").split(" ");
            System.out.println(arr[0]);
            var newarr=Arrays.stream(arr).filter(n->n.length()>=5).collect(Collectors.toList());
            System.out.println((newarr));
            for(String a:newarr){
                if(map.containsKey(a)){
                    map.put(a, map.get(a)+1);
                }
                else{
                    map.put(a, 1);
                }
            }
            map.forEach((x,y)->System.out.println(x+"count"+y));
            List<Map.Entry<String,Integer>> list=new ArrayList<>(map.entrySet());
            Collections.sort(list,(a,b)->b.getValue().compareTo(a.getValue()));

            list.stream().limit(10).forEach(k->System.out.println("words "+k.getKey()+" count "+k.getValue()));



            
        }
        



    }
}
