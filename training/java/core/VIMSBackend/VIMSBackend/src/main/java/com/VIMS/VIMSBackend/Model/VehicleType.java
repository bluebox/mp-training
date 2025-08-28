package com.VIMS.VIMSBackend.Model;

import java.util.HashMap;
import java.util.Map;

public enum VehicleType {
	TWO("2"),
    THREE("3"),
	FOUR("4"),
	EIGHT("8");
	String string;

  private VehicleType(String string) {
		this.string=string;
	}
	
	 public String getType() {
	        return this.string;
	    }
	
	private static final Map<String,VehicleType> lookup=new HashMap<>();
	
	static {
		for(VehicleType vehicle:VehicleType.values()) {
			lookup.put(vehicle.getType(), vehicle);
		}
	}
	
	public static VehicleType getVehicleType(String status) {
		return lookup.get(status);
	}

}
