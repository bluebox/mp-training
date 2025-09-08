package dev.kaushik.library.model.enums;

public enum BookAvailability {
    AVAILABLE("A"),
    ISSUED("I");

    private final String code;

    BookAvailability(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BookAvailability fromCode(String code) { 
        for (BookAvailability availability : BookAvailability.values()) {
            if (availability.code.equalsIgnoreCase(code)) {
                return availability;
            }
        }
        throw new IllegalArgumentException("No book availability with code: " + code);
    }
}