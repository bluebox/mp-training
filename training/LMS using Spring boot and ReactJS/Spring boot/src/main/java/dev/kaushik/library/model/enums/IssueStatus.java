package dev.kaushik.library.model.enums;

public enum IssueStatus {
    ISSUED("I"),
    RETURNED("R");

    private final String code;

    IssueStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static IssueStatus fromCode(String code) { 
        for (IssueStatus status : IssueStatus.values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No issue status with code: " + code);
    }
}