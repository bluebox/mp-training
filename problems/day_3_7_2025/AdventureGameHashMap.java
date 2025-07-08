package day_3_7_2025;
import java.util.*;
import java.util.Map.Entry;

class Location{
	public String Description;
public Map<String,String> NextPlacesmap;
	
	public Location(String Description,Map<String,String> map) {
		this.Description=Description;
		this.NextPlacesmap=map;
	}
	
}
public class AdventureGameHashMap {
	
	public static void main(String [] args) {
       Map<String,Location> map=new HashMap<>();
       map.put("Kullu",new Location("Hill Station",new HashMap<>()));
       map.get("kullu").NextPlacesmap.computeIfAbsent("Himacha;", (k) -> "State of hills (" + k + ")");
       System.out.println(map.get("kullu").hashCode());
       Set<Entry<String, Location>> entries=map.entrySet();
       System.out.println(map.get("kullu").getClass().getSimpleName());
       }
}
