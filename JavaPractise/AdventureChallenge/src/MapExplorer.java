import java.util.*;
public class MapExplorer {
    public static void main(String[] args) {
        Map<String,Place> map=new HashMap<>();
        Place stream = new Place("You are near a stream.");
        stream.addDirection("W","valley");
        stream.addDirection("N","well house");
        map.put("stream", stream);
        Place wellHouse=new Place("You are inside a small well house.");
        wellHouse.addDirection("W","road");
        wellHouse.addDirection("N","lake");
        wellHouse.addDirection("S","stream");
        map.put("well house", wellHouse);
        for (String name:map.keySet()) {
            Place p = map.get(name);
            System.out.println("Place: "+name);
            System.out.println("Detail: " +p.getDetail());
            System.out.println("Directions:");
            p.getDirections().forEach((d,next) -> System.out.println(d+" -> "+next));
            System.out.println();
        }

    }
}
