import java.util.HashMap;
import java.util.Map;



public class Adventure {
    
    public static void main(String[] args) {
    Map<String,Location> map1=new HashMap<>();
    Map<String,String> map2=new HashMap<>();
    Map<String,String> map3=new HashMap<>();
    map3.put("W","valley");
    map3.put("N", "Well house");
    map2.put("W","road");
    map2.put("N", "lake"); 
    map2.put("S","stream"); 
    Location l1=new Location("near a stream with a rocky bed", map3);
    Location l2=new Location("inside a well House for a small spring", map2);
    
        
        System.out.println(l1);
        System.out.println(l2);
    }
}
