package com.VIMS.VIMSBackend.Model;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    ADMIN("A"),
    USER("U");

    private final String type;

    Role(String type) {
        this.type = type;
    }

    private static final Map<String, Role> lookup = new HashMap<>();
    static {
        for (Role r : Role.values()) {
            lookup.put(r.getType(), r);
        }
    }

    public static Role getRole(String type) {
        return lookup.get(type);
    }

    public String getType() {
        return this.type;
    }
}
