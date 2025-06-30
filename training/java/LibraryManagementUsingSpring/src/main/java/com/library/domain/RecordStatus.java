package com.library.domain;


public enum RecordStatus {
	ISSUED("I"), RETURNED("R");

	private final String code;

	RecordStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static RecordStatus fromCode(String code) {
		for (RecordStatus status : RecordStatus.values()) {
			if (status.code.equalsIgnoreCase(code)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid status code: " + code);
	}
}