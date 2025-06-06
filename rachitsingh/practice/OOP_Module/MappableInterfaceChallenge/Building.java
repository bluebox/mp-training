package MappableInterfaceChallenge;

import MappableInterfaceChallenge.enums.*;

public class Building implements Mappable {
	private String buildingLabel;
	private UsageType usedFor;
	private PointMarker marker;

	public Building(String buildingLabel, UsageType usedFor, PointMarker marker) {
		this.buildingLabel = buildingLabel;
		this.usedFor = usedFor;
		this.marker = marker;
	}

	@Override
	public String getLabel() {
		return buildingLabel + " (" + usedFor + ")";
	}

	@Override
	public String getMarker() {
		return marker.toString().replace('_', ' ');
	}

	@Override
	public Geometry getShape() {
		return Geometry.POINT;
	}

	@Override
	public String toJSON() {
		return String.format(JSON_Property,
				"\"type\": \"" + getShape() + "\", " + "\"label\": \"" + getLabel() + "\", " + "\"marker\": \""
						+ getMarker() + "\", " + "\"name\": \"" + buildingLabel + "\", " + "\"usage\": \"" + usedFor
						+ "\"");
	}
}
