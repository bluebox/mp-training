package com.VIMS.VIMSBackend.Model;

import java.util.HashMap;
import java.util.Map;


public enum IssueInsuranceStatus {
	ACTIVE("A"),INACTIVE("I"),PENDING("P"),NOTACCEPTED("N");
	private final String type;

	IssueInsuranceStatus(String type) {
        this.type = type;
    }

    private static final Map<String, IssueInsuranceStatus> lookup = new HashMap<>();

    static {
        for (IssueInsuranceStatus s : IssueInsuranceStatus.values()) {
            lookup.put(s.getType(), s);
        }
    }

    public static IssueInsuranceStatus getstatus(String type) {
        return lookup.get(type);
    }

    public String getType() {
        return this.type;
    }
		
}
