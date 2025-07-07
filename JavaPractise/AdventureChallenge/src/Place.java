import java.util.*;
public class Place {
    private String detail;
    private Map<String, String> directions;
    public Place(String detail){
        this.detail = detail;
        this.directions = new HashMap<>();
    }
    public void addDirection(String dir,String nextPlace){
        directions.put(dir,nextPlace);
    }
    public String getDetail(){
        return detail;
    }
    public Map<String, String> getDirections(){
        return directions;
    }
}
