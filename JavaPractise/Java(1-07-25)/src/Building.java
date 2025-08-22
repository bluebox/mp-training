
public class Building implements Mappable {
	private final String name;
	private final String usage;
	private final Color color;
	private final PointMarker markerType;
	public Building(String name,String usage,Color color,PointMarker markerType)
	{
		this.name=name;
		this.usage=usage;
		this.color=color;
		this.markerType=markerType;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return name+"("+usage+ ")";
	}

	@Override
	public Geometry getShape() {
		// TODO Auto-generated method stub
		return Geometry.POINT;
	}

	@Override
	public String getMarker() {
        return switch (markerType) {
            case CIRCLE     -> color + " CIRCLE";
            case PUSH_PIN   -> color + " PUSH_PIN";
            case STAR       -> color + " STAR";
            case SQUARE     -> color + " SQUARE";
            case TRIANGLE   -> color + " TRIANGLE";
        };

	}
	@Override
	public String toJSON() {
	    String base = Mappable.super.toJSON().trim();
	    return base.substring(0, base.length() - 1)
	         + String.format(",\"name\":\"%s\",\"usage\":\"%s\"}", name, usage);
	}

}
