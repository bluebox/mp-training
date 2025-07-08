
enum UtilityType{
    ELECTRICAL,
    FIBER_OPTIC_
}
public class UtilityLine implements Mappable{
    private String name;
    private UtilityType type;
    public String getMarker(){
        return "the marker"+this.name;
    }
    public String getLabel(){
         switch(type){
            case ELECTRICAL:
            {return "this is electrical";
            }
            case FIBER_OPTIC_:
            {return "this is fiber optic";
            }
            default:
            {return "none of this" ;
        }
        }
    }
    public Geometry getShape(){
        return Geometry.LINE;
    }
}
