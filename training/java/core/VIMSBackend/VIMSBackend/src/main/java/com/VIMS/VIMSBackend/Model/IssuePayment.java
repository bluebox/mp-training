package com.VIMS.VIMSBackend.Model;

import java.util.HashMap;
import java.util.Map;

public enum IssuePayment {
	DOWNPAYMENT("D"),EMI("E");
	
	private final String type;

	IssuePayment(String type) {
        this.type = type;
    }

    private static final Map<String, IssuePayment> lookup = new HashMap<>();

    static {
        for (IssuePayment s : IssuePayment.values()) {
            lookup.put(s.getType(), s);
        }
    }

    public static IssuePayment getstatus(String type) {
        return lookup.get(type);
    }

    public String getType() {
        return this.type;
    }
}
