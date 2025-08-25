package com.EventManagement.EMS_Backend.Model;



import java.util.HashMap;
import java.util.Map;

public enum Attendenceenum {

	PRESENT("present"), ABSENT("absent");

	String string;

	Attendenceenum(String string) {
		this.string = string;
	}

	public String getType() {
		return this.string;
	}

	private static final Map<String, Attendenceenum> lookup = new HashMap<>();

	static {
		for (Attendenceenum attendenceenum : Attendenceenum.values()) {
			lookup.put(attendenceenum.getType(), attendenceenum);
		}
	}

	public static Attendenceenum getAttendenceenum(String Attendenceenum) {
		return lookup.get(Attendenceenum);
	}

}