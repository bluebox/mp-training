package PustakaLokam.library.enums;

public enum BookCondition {
    ACTIVE("A", "Active"),
    INACTIVE("I", "Inactive");

    private final String code;
    private final String label;

    BookCondition(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static BookCondition fromLabel(String label) {
        for (BookCondition condition : BookCondition.values()) {
            if (condition.label.equalsIgnoreCase(label)) {
                return condition;
            }
        }
        throw new IllegalArgumentException("Unknown condition label: " + label);
    }
}




