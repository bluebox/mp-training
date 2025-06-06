package MappableInterfaceChallenge;

import MappableInterfaceChallenge.enums.*;

public class UtilityLine implements Mappable {
	private String utilityLabel;
	private UtilityType utility;
	private LineMarker marker;

	public UtilityLine(String utilityLabel, UtilityType utility, LineMarker marker) {
		this.utilityLabel = utilityLabel;
		this.utility = utility;
		this.marker = marker;
	}

	@Override
	public String getLabel() {
		return utilityLabel + " (" + utility + ")";
	}

	@Override
	public String getMarker() {
		return marker.toString().replace('_', ' ');
	}

	@Override
	public Geometry getShape() {
		return Geometry.LINE;
	}

	@Override
	public String toJSON() {
		return String.format(JSON_Property,
				"\"type\": \"" + getShape() + "\", " + "\"label\": \"" + getLabel() + "\", " + "\"marker\": \""
						+ getMarker() + "\", " + "\"name\": \"" + utilityLabel + "\", " + "\"utility\": \"" + utility
						+ "\"");
	}
}
