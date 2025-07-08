enum Geometry{
    LINE,
    POINT,
    POLYGON
}
enum Color{
    BLACK,BLUE,GREEN
}
enum PointMarkers{
    CIRCLE,DIOMAND
}
enum LineMarkers{
    DASHED,DOTTED,SOLID
}

public interface Mappable {
    
    static void maplt(Mappable mappable){

        mappable.toJSON();
    };
    String JSON_PROPERTY="properties";
    default String toJSON(){
        return JSON_PROPERTY+"name"+this.getMarker()+"type"+this.getLabel()+"shape"+this.getShape();
    }
    String getMarker();
    String getLabel();
    Geometry getShape();
}
