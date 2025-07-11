package com.product.enums;

public enum RequestStatus {
	APPROVED("A", "Approved"), 
	DECLINED("D", "Declined"),
	PENDING("P", "Pending");

	private final String code;
	private final String label;
	RequestStatus(String code, String label){
		this.code = code;
		this.label = label;
	}
	public String getCode() {
		return code;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static RequestStatus fromLabel(String label) {
		for(RequestStatus status: RequestStatus.values()) {
			if(status.getLabel().equalsIgnoreCase(label)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown Product Request Status label: " + label);
	}
	
	public static RequestStatus fromCode(String code) {
		for(RequestStatus status: RequestStatus.values()) {
			if(status.getCode().equalsIgnoreCase(code)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown Product Request Status code: " + code);
	}
}
