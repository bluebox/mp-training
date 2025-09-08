package dev.kaushik.library.model.enums;

public enum BookStatus {
    ACTIVE("A"),
    INACTIVE("I");

    private final String code;

    BookStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BookStatus fromCode(String code) { 
        for (BookStatus status : BookStatus.values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No book status with code: " + code);
    }
}