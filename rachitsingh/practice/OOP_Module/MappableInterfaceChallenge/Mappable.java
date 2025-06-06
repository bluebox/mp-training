package MappableInterfaceChallenge;

import MappableInterfaceChallenge.enums.*;

public interface Mappable {
	final String JSON_Property = "\"property\": {%s}";

	String getLabel();

	String getMarker();

	Geometry getShape();

	// this method is default, it simply means that it is not mandatory for classes
	// implementing this Mappable interface to define this method
	default String toJSON() {
		return String.format(JSON_Property, "\"type\": \"" + getShape() + "\", " + "\"label\": \"" + getLabel() + "\", "
				+ "\"marker\": \"" + getMarker() + "\", ");
	}

	static void mapIt(Mappable mapObj) {
		System.out.println(mapObj.toJSON());
	}
}
