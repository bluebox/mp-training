
 enum UsageType{
    BUSINESS,
    ENTERTAINMENT

}
public class Building implements Mappable {
    
    private String name;
    private UsageType type; 
    public String getMarker(){
        return "Mark"+this.name;
    }
    public String getLabel(){
        switch (type) {
            case BUSINESS:
                return "this is business";
                
            case ENTERTAINMENT:
                return "this is entertainment";
        
            default:
                return "none of this";
                
        }
    }
    public Geometry getShape(){
        return Geometry.POLYGON;
    }
    public String toJSON(){
        return "";
    }
}
