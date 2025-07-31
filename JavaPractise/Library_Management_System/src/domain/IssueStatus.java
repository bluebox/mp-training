package domain;

public enum IssueStatus {
    Issued("I"),
    Returned("R");

    private final String code;

    IssueStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static IssueStatus fromCode(String code) {
        switch (code) {
            case "I": return Issued;
            case "R": return Returned;
            default: throw new IllegalArgumentException("Unknown status: " + code);
        }
    }
}