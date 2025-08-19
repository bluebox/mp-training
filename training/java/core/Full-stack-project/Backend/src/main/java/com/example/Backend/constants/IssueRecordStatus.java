package com.example.Backend.constants;

import java.util.stream.Stream;

public enum IssueRecordStatus {
	ISSUED("Issued", "I"), RETURNED("Returned", "R");

	private String displayName;
	private String dbName;

	IssueRecordStatus(String displayName, String dbName) {
		this.displayName = displayName;
		this.dbName = dbName;
	}

	public String getStringValue() {
		return this.dbName;
	}

	@Override
	public String toString() {
		return this.displayName;
	}

	public static IssueRecordStatus getEnumConstant(String value) {
		return Stream.of(IssueRecordStatus.values()).filter(e -> e.getStringValue().equals(value)).findFirst()
				.orElse(null);
	}
}
