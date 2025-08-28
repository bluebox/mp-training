package com.VIMS.VIMSBackend.Model;

import java.util.HashMap;
import java.util.Map;

public enum upgradeRequestStatus {
	ACCEPT("A"),NOTACCEPT("N");
	private final String type;

	upgradeRequestStatus(String type) {
        this.type = type;
    }

    private static final Map<String, upgradeRequestStatus> lookup = new HashMap<>();

    static {
        for (upgradeRequestStatus s : upgradeRequestStatus.values()) {
            lookup.put(s.getType(), s);
        }
    }

    public static upgradeRequestStatus getstatus(String type) {
        return lookup.get(type);
    }

    public String getType() {
        return this.type;
    }

}