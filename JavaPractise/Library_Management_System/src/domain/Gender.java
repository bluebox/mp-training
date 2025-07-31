package domain;

public enum Gender {
    Male("M"),
    Female("F"),
    Other("O");
    private final String code;
    Gender(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    public static Gender fromCode(String code) {
        switch (code) {
            case "M": return Male;
            case "F": return Female;
            case "O": return Other;
            default: throw new IllegalArgumentException("Invalid gender code: " + code);
        }
    }
}