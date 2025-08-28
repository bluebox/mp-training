package com.VIMS.VIMSBackend.Model;

import java.util.HashMap;
import java.util.Map;

public enum ClaimFormStatus {
	PENDING("P"),COMPLETED("C");
	private final String type;

	ClaimFormStatus(String type) {
        this.type = type;
    }

    private static final Map<String, ClaimFormStatus> lookup = new HashMap<>();

    static {
        for (ClaimFormStatus s : ClaimFormStatus.values()) {
            lookup.put(s.getType(), s);
        }
    }

    public static ClaimFormStatus getstatus(String type) {
        return lookup.get(type);
    }

    public String getType() {
        return this.type;
    }

}
